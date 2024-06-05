package pe.upc.learningcenter.learning.domain.model.aggregates;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import pe.upc.learningcenter.learning.domain.model.valueobjects.AcmeStudentRecordId;
import pe.upc.learningcenter.learning.domain.model.valueobjects.EnrollmentStatus;
import pe.upc.learningcenter.learning.domain.model.valueobjects.ProgressRecord;
import pe.upc.learningcenter.shared.domain.model.aggregate.AuditableAbstractAggregateRoot;

@Entity
@Getter
public class Enrollment extends AuditableAbstractAggregateRoot<Enrollment> {

    @Embedded
    private AcmeStudentRecordId acmeStudentRecordId;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    /**
     * The progress record for this enrollment
     */
    @Embedded
    private ProgressRecord progressRecord;

    private EnrollmentStatus status;

    public Enrollment() {

    }

}
