package pe.upc.learningcenter.learning.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public enum EnrollmentStatus {
    REQUESTED,
    CONFIRMED,
    REJECTED,
    CANCELLED,
}
