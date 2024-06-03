package pe.upc.learningcenter.profiles.application.internal;

import org.springframework.stereotype.Service;
import pe.upc.learningcenter.profiles.domain.model.aggregates.Profile;
import pe.upc.learningcenter.profiles.domain.model.queries.GetProfileByEmailQuery;
import pe.upc.learningcenter.profiles.domain.model.queries.GetProfileByIdQuery;
import pe.upc.learningcenter.profiles.domain.model.queries.GetProfileByNameQuery;
import pe.upc.learningcenter.profiles.domain.services.ProfileQueryService;
import pe.upc.learningcenter.profiles.infrastructure.persistence.jpa.repositories.ProfileRepository;

import java.util.Optional;

@Service
public class ProfileQueryServiceImpl implements ProfileQueryService {

    private final ProfileRepository profileRepository;

    public ProfileQueryServiceImpl(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    public Optional<Profile> handle(GetProfileByIdQuery query) {
        return profileRepository.findById(query.id());
    }

    @Override
    public Optional<Profile> handle(GetProfileByNameQuery query) {
        return profileRepository.findByName(query.name());
    }

    @Override
    public Optional<Profile> handle(GetProfileByEmailQuery query) {
        return profileRepository.findByEmail(query.emailAddress());
    }
}
