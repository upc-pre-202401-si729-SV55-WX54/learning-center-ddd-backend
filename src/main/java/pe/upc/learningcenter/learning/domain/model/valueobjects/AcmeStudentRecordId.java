package pe.upc.learningcenter.learning.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

import java.util.UUID;

@Embeddable
public record AcmeStudentRecordId(String studentRecordId) {
    public AcmeStudentRecordId {
        if (studentRecordId == null || studentRecordId.isEmpty()) {
            throw new IllegalArgumentException("studentRecordId cannot be null or empty");
        }
    }

    public AcmeStudentRecordId() { this(UUID.randomUUID().toString()); }
}
