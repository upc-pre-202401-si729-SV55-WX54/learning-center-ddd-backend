package pe.upc.learningcenter.iam.application.internal.commandservices;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.stereotype.Service;
import pe.upc.learningcenter.iam.application.internal.outboundservices.hashing.HashigService;
import pe.upc.learningcenter.iam.application.internal.outboundservices.tokens.TokenService;
import pe.upc.learningcenter.iam.domain.model.aggregates.User;
import pe.upc.learningcenter.iam.domain.model.commands.SignInCommand;
import pe.upc.learningcenter.iam.domain.model.commands.SignUpCommand;
import pe.upc.learningcenter.iam.domain.services.UserCommandService;
import pe.upc.learningcenter.iam.infraestructure.persistence.jpa.repositories.RoleRepository;
import pe.upc.learningcenter.iam.infraestructure.persistence.jpa.repositories.UserRepository;

import java.util.Optional;

@Service
public class UserCommandServiceImpl implements UserCommandService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final HashigService hashigService;
    private final TokenService tokenService;

    public UserCommandServiceImpl(final UserRepository userRepository, RoleRepository roleRepository, HashigService hashigService, TokenService tokenService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.hashigService = hashigService;
        this.tokenService = tokenService;
    }

    @Override
    public Optional<User> handle(SignUpCommand command) {
        if(userRepository.existsByUsername(command.username())){
            throw new RuntimeException("Username already exists");
        }

        var roles = command.roles().stream().map(role -> roleRepository.findByName(role.getName()).orElseThrow(()-> new RuntimeException("Role name not found."))).toList();
        var user = new User(command.username(), hashigService.encode(command.password()), roles);
        userRepository.save(user);

        return userRepository.findByUsername(command.username());
    }

    @Override
    public Optional<ImmutablePair<User, String>> handle(SignInCommand command) {
        var user = userRepository.findByUsername(command.username());
        if(user.isEmpty())
            throw new RuntimeException("Username not found");

        if(!hashigService.matches(command.password(), user.get().getPassword()))
            throw new RuntimeException("Wrong password");

        var token = tokenService.generateToken(user.get().getUsername());

        return Optional.of(ImmutablePair.of(user.get(), token));
    }
}
