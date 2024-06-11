package pe.upc.learningcenter.iam.domain.services;

import pe.upc.learningcenter.iam.domain.model.commands.SeedRolesCommand;

public interface RoleCommandService {
    void handle(SeedRolesCommand command);
}
