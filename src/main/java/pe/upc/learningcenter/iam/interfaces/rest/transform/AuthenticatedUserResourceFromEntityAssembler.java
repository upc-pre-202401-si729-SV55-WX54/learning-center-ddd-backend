package pe.upc.learningcenter.iam.interfaces.rest.transform;

import pe.upc.learningcenter.iam.domain.model.aggregates.User;
import pe.upc.learningcenter.iam.interfaces.rest.resources.AuthenticatedUserResource;
import pe.upc.learningcenter.iam.interfaces.rest.resources.UserResource;

public class AuthenticatedUserResourceFromEntityAssembler {

    public static AuthenticatedUserResource toResourceFromEntity(User entity, String token){
        return new AuthenticatedUserResource(entity.getId(), entity.getUsername(), token);
    }
}
