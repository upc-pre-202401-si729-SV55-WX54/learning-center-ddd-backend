package pe.upc.learningcenter.iam.infraestructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.upc.learningcenter.iam.domain.model.entities.Role;
import pe.upc.learningcenter.iam.domain.model.valueobjects.Roles;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(Roles name);
}
