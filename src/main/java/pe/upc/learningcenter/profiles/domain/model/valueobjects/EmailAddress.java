package pe.upc.learningcenter.profiles.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record EmailAddress(String emailAddress) {
    public EmailAddress() { this(null); }
}
