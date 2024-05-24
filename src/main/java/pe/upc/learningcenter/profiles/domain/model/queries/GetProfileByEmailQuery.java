package pe.upc.learningcenter.profiles.domain.model.queries;

import pe.upc.learningcenter.profiles.domain.model.valueobjects.EmailAddress;

public record GetProfileByEmailQuery(EmailAddress emailAddress) {
}
