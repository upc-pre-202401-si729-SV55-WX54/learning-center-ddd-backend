package pe.upc.learningcenter.profiles.domain.model.valueobjects;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Embeddable
public record StreetAddress(
        @NotBlank String street,
        @NotBlank String number,
        @NotBlank String city,
        @NotBlank String postalCode,
        @NotBlank String country
) {

    public StreetAddress() {
        this(null, null, null, null, null);
    }

    public StreetAddress {
        if (street == null || street.isBlank()) {
            throw new IllegalArgumentException("Street cannot be null or blank");
        }
        if (number == null || number.isBlank()) {
            throw new IllegalArgumentException("Number cannot be null or blank");
        }
        if (city == null || city.isBlank()) {
            throw new IllegalArgumentException("City cannot be null or blank");
        }
        if (postalCode == null || postalCode.isBlank()) {
            throw new IllegalArgumentException("PostalCode cannot be null or blank");
        }
        if (country == null || country.isBlank()) {
            throw new IllegalArgumentException("Country cannot be null or blank");
        }
    }

    public String getStreetAddress() {
        return String.format("%s %s, %s, %s, %s", street, number, city, postalCode, country);
    }
}
