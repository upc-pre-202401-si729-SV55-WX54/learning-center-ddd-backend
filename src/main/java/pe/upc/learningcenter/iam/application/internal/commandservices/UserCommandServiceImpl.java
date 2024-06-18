package pe.upc.learningcenter.iam.application.internal.commandservices;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springdoc.core.utils.PropertyResolverUtils;
import org.springframework.stereotype.Service;
import pe.upc.learningcenter.iam.application.internal.outboundservices.hashing.HashingService;
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
    private final HashingService hashingService;
    private final TokenService tokenService;

    public UserCommandServiceImpl(UserRepository userRepository, RoleRepository roleRepository, HashingService hashingService, TokenService tokenService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.hashingService = hashingService;
        this.tokenService = tokenService;
    }

    @Override
    public Optional<ImmutablePair<User, String>> handle(SignInCommand command) {
        var user = userRepository.findByUsername(command.username());
        if (user.isEmpty())
            throw new RuntimeException("User not found");

        if(!hashingService.matches(command.password(), user.get().getPassword()))
            throw new RuntimeException("Invalid password");

        var token = tokenService.generateToken(user.get().getUsername());

        return Optional.of(ImmutablePair.of(user.get(), token));
    }

    @Override
    public Optional<User> handle(SignUpCommand command) {
        if(userRepository.existsByUsername(command.username()))
            throw new RuntimeException("Username already exists");

        var roles = command.roles().stream().map(role -> roleRepository.findByName(role.getName()).orElseThrow(()-> new RuntimeException("Role not found"))).toList();

        var user = new User(command.username(), hashingService.encode(command.password()), roles);
        userRepository.save(user);

        return userRepository.findByUsername(command.username());
    }
}
