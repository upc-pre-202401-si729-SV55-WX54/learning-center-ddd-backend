package pe.upc.learningcenter.iam.interfaces.rest.transform;

import pe.upc.learningcenter.iam.domain.model.commands.SignInCommand;
import pe.upc.learningcenter.iam.interfaces.rest.resources.SignInResource;

public class SignInCommandFromResourceAssembler {
    public static SignInCommand toCommandFromResource(SignInResource resource) {
        return new SignInCommand(resource.username(), resource.password());
    }
}
