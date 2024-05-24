package pe.upc.learningcenter.profiles.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record PersonName(String firstName, String lastName) {
    public PersonName() {
        this(null, null);
    }

    public PersonName {
        if (firstName == null || firstName.isBlank()) {
            throw new IllegalArgumentException("firstName cannot be null");
        }
        if (lastName == null || lastName.isBlank()) {
            throw new IllegalArgumentException("lastName cannot be null");
        }
    }

    public String getFullName() {
        return String.format("%s %s", firstName, lastName);
    }
}
