package pe.upc.learningcenter.iam.interfaces.rest.transform;

import jakarta.annotation.Resource;
import pe.upc.learningcenter.iam.domain.model.commands.SignInCommand;
import pe.upc.learningcenter.iam.interfaces.rest.resources.SignInResource;

public class SignInCommandFromResourceAssembler {

    public static SignInCommand toCommandFromResource(final SignInResource resource) {
        return new SignInCommand(resource.username(), resource.password());
    }
}
