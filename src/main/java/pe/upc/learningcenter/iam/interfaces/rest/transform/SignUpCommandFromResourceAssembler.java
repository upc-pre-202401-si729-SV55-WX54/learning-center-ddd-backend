package pe.upc.learningcenter.iam.interfaces.rest.transform;

import pe.upc.learningcenter.iam.domain.model.commands.SignInCommand;
import pe.upc.learningcenter.iam.domain.model.commands.SignUpCommand;
import pe.upc.learningcenter.iam.interfaces.rest.resources.SignInResource;
import pe.upc.learningcenter.iam.interfaces.rest.resources.SignUpResource;

public class SignUpCommandFromResourceAssembler {

    public static SignUpCommand toCommandFromResource(final SignUpResource resource) {
        return new SignUpCommand(resource.username(), resource.password(), resource.roles());
    }
}
