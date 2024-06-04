package pe.upc.learningcenter.learning.infrastructure.persistence.jpa.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.upc.learningcenter.learning.domain.model.aggregates.Student;
import pe.upc.learningcenter.learning.domain.model.valueobjects.ProfileId;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByProfileId(ProfileId profileId);
}
