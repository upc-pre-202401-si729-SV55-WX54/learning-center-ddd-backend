package pe.upc.learningcenter.learning.domain.model.entities;

import jakarta.persistence.*;
import pe.upc.learningcenter.learning.domain.model.aggregates.Enrollment;
import pe.upc.learningcenter.shared.domain.model.entities.AuditableModel;

@Entity
public class ProgressRecordItem extends AuditableModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "enrollment_id")
    private Enrollment enrollment;


}