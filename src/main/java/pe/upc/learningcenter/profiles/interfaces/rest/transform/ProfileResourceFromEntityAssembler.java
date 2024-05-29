package pe.upc.learningcenter.profiles.interfaces.rest.transform;

import pe.upc.learningcenter.profiles.domain.model.aggregates.Profile;
import pe.upc.learningcenter.profiles.interfaces.rest.resources.ProfileResource;

public class ProfileResourceFromEntityAssembler {
    public static ProfileResource transformResourceFromEntity(Profile entity){
        return new ProfileResource(entity.getId(), entity.getFullName(), entity.getEmailAdress(), entity.getStreetAddress());
    }
}
