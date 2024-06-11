package pe.upc.learningcenter.iam.domain.model.commands;

import pe.upc.learningcenter.iam.domain.model.entities.Role;

import java.util.List;

public record SignUpCommand(String username, String password, List<Role> roles) {
}
