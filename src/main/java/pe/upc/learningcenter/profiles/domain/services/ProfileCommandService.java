package pe.upc.learningcenter.profiles.domain.services;

import org.springframework.stereotype.Service;
import pe.upc.learningcenter.profiles.domain.model.aggregates.Profile;
import pe.upc.learningcenter.profiles.domain.model.commands.CreateProfileCommand;

import java.util.Optional;


@Service
public interface ProfileCommandService {
    Optional<Profile> handle(CreateProfileCommand command);
}
