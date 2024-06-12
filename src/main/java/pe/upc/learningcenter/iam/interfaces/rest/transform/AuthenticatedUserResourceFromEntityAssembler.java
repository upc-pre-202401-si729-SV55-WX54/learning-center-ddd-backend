package pe.upc.learningcenter.iam.interfaces.rest.transform;

import pe.upc.learningcenter.iam.domain.model.aggregates.User;
import pe.upc.learningcenter.iam.interfaces.rest.resources.AuthenticateUserResource;

public class AuthenticatedUserResourceFromEntityAssembler {

    public static AuthenticateUserResource toResourceFromEntity(User entity, String token){
        return new AuthenticateUserResource(entity.getUsername(), token);
    }
}
