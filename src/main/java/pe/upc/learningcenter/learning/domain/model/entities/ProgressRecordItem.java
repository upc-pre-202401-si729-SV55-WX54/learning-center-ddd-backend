package pe.upc.learningcenter.learning.domain.model.entities;

import jakarta.persistence.*;
import pe.upc.learningcenter.learning.domain.model.aggregates.Enrollment;
import pe.upc.learningcenter.learning.domain.model.valueobjects.ProgressRecord;
import pe.upc.learningcenter.learning.domain.model.valueobjects.ProgressStatus;
import pe.upc.learningcenter.shared.domain.model.entities.AuditableModel;

import java.util.Date;

@Entity
public class ProgressRecordItem extends AuditableModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @ManyToOne
    @JoinColumn(name = "enrollment_id")
    private Enrollment enrollment;

    private Long tutorialId;

    private ProgressStatus progressStatus;

    private Date startedAt;

    private Date completedAt;

    public ProgressRecordItem() {

    }


}
