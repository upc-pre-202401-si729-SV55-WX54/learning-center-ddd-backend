package pe.upc.learningcenter.iam.interfaces.rest.resources;

import pe.upc.learningcenter.iam.domain.model.valueobjects.Roles;

import java.util.List;

public record UserResource(Long id, String username, List<Roles> roles) {
}
