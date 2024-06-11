package pe.upc.learningcenter.iam.interfaces.rest.resources;

public record AuthenticatedUserResource(Long id, String username, String token) {
}
