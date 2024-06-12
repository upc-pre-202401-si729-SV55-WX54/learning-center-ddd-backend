package pe.upc.learningcenter.iam.application.internal.commandservices;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.stereotype.Service;
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

    public UserCommandServiceImpl(final UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public Optional<User> handle(SignUpCommand command) {
        if(userRepository.existsByUsername(command.username())){
            throw new RuntimeException("Username already exists");
        }

        var roles = command.roles().stream().map(role -> roleRepository.findByName(role.getName()).orElseThrow(()-> new RuntimeException("Role name not found."))).toList();
        var user = new User(command.username(), command.password(), roles);
        userRepository.save(user);

        return userRepository.findByUsername(command.username());
    }

    @Override
    public Optional<ImmutablePair<User, String>> handle(SignInCommand command) {
        var user = userRepository.findByUsername(command.username());
        if(user.isEmpty())
            throw new RuntimeException("Username not found");
        
        //@TODO : Impements hashing services
        if(!user.get().getPassword().contentEquals(command.password()))
            throw new RuntimeException("Wrong password");

        //@TODO: Implements token services
        var token = "sadf423f424f2.f42342342342.f23432423423eqweqwdasdas==";

        return Optional.of(ImmutablePair.of(user.get(), token));
    }
}
