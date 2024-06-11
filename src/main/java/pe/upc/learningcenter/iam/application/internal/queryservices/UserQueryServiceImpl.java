package pe.upc.learningcenter.iam.application.internal.queryservices;

import org.springframework.stereotype.Service;
import pe.upc.learningcenter.iam.domain.model.aggregates.User;
import pe.upc.learningcenter.iam.domain.model.queries.GetAllUsersQuery;
import pe.upc.learningcenter.iam.domain.model.queries.GetUserByIdQuery;
import pe.upc.learningcenter.iam.domain.model.queries.GetUserByUsernameQuery;
import pe.upc.learningcenter.iam.domain.services.UserQueryService;
import pe.upc.learningcenter.iam.infraestructure.persistence.jpa.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserQueryServiceImpl implements UserQueryService {

    private final UserRepository userRepository;

    public UserQueryServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> handle(GetAllUsersQuery query) {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> handle(GetUserByIdQuery query){
        return userRepository.findById(query.id());
    }

    @Override
    public Optional<User> handle(GetUserByUsernameQuery query) {
        return userRepository.findByUsername(query.username());
    }
}
