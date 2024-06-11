package pe.upc.learningcenter.iam.application.internal.commandservices;

import org.springframework.stereotype.Service;
import pe.upc.learningcenter.iam.domain.model.commands.SeedRolesCommand;
import pe.upc.learningcenter.iam.domain.services.RoleCommandService;
import pe.upc.learningcenter.iam.infraestructure.persistence.jpa.repositories.RoleRepository;

@Service
public class RoleCommandServiceImpl implements RoleCommandService {

    private final RoleRepository roleRepository;

    public RoleCommandServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void handle(SeedRolesCommand command) {

    }
}
