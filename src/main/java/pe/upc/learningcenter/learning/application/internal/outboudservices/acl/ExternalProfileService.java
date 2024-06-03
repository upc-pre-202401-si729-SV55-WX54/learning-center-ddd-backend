package pe.upc.learningcenter.learning.application.internal.outboudservices.acl;

import org.springframework.stereotype.Service;
import pe.upc.learningcenter.learning.domain.model.valueobjects.ProfileId;
import pe.upc.learningcenter.profiles.interfaces.acl.ProfilesContextFacade;

import java.util.Optional;

/**
 * ExternalProfileServices
 *
 *
 */
@Service
public class ExternalProfileService {

    private final ProfilesContextFacade profilesContextFacade;

    public ExternalProfileService(ProfilesContextFacade profilesContextFacade) {
        this.profilesContextFacade = profilesContextFacade;
    }

    /**
     * Fetch profileId by Email
     *
     * @param email
     * @return profileId if created, empty otherwise
     */
    public Optional<ProfileId> fetchProfileIdByEmail(String email) {
        var profileId = profilesContextFacade.fetchProfileIdByEmail(email);
        if (profileId == 0L) return Optional.empty();
        return Optional.of(new ProfileId(profileId));
    }

    /**
     * Create Profile
     *
     * @param firstName
     * @param lastName
     * @param email
     * @param street
     * @param number
     * @param city
     * @param postalCode
     * @param country
     * @return profileId if created, empty otherwise
     */
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
