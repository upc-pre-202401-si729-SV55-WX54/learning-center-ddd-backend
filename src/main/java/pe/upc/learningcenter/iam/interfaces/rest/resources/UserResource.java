package pe.upc.learningcenter.iam.interfaces.rest.resources;

import java.util.List;

public record UserResource(String username, List<String> roles) {
}
