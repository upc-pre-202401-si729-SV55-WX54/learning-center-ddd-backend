package pe.upc.learningcenter.profiles.domain.services;

import org.springframework.stereotype.Service;
import pe.upc.learningcenter.profiles.domain.model.aggregates.Profile;
import pe.upc.learningcenter.profiles.domain.model.queries.GetProfileByEmailQuery;
import pe.upc.learningcenter.profiles.domain.model.queries.GetProfileByIdQuery;
import pe.upc.learningcenter.profiles.domain.model.queries.GetProfileByNameQuery;

import java.util.Optional;

@Service
public interface ProfileQueryService {
    Optional<Profile> handle(GetProfileByIdQuery query);
    Optional<Profile> handle(GetProfileByNameQuery query);
    Optional<Profile> handle(GetProfileByEmailQuery query);
}
