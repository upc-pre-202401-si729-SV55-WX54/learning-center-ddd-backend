package pe.upc.learningcenter.iam.interfaces.rest.resources;

import pe.upc.learningcenter.iam.domain.model.entities.Role;

import java.util.List;

public record SignUpResource(String username, String password, List<Role> roles) {
}
