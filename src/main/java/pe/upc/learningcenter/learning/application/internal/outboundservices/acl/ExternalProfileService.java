package pe.upc.learningcenter.learning.application.internal.outboundservices.acl;

import org.springframework.stereotype.Service;
import pe.upc.learningcenter.learning.domain.model.valueobjects.ProfileId;
import pe.upc.learningcenter.profiles.interfaces.acl.ProfilesContextFacade;

import java.util.Optional;

/**
 * ExternalProfileService
 * <p>
 *     This is an outbound service used by the Learning Context to interact with the Profile Context.
 *     It is implemented as part of an anti-corruption layer (ACL) to decouple the Learning Context from the Profile Context.
 * </p>
 */
@Service
public class ExternalProfileService {

    private final ProfilesContextFacade profilesContextFacade;

    public ExternalProfileService(ProfilesContextFacade profilesContextFacade) {
        this.profilesContextFacade = profilesContextFacade;
    }

    public Optional<ProfileId> fetchProfileIdByEmail(String email) {
        var profileId = profilesContextFacade.fetchProfileIdByEmail(email);
        if (profileId == 0L) return Optional.empty();
        return Optional.of(new ProfileId(profileId));
    }

    public Optional<ProfileId> createProfile(String firstName,
                                             String lastName,
                                             String email,
                                             String street,
                                             String number,
                                             String city,
                                             String postalCode,
                                             String country) {
        var profileId = profilesContextFacade.createProfile(firstName, lastName, email, street, number, city, postalCode, country);
        if (profileId == 0L) return Optional.empty();
        return Optional.of(new ProfileId(profileId));
    }

}
