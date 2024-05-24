package pe.upc.learningcenter.profiles.domain.model.queries;

import pe.upc.learningcenter.profiles.domain.model.valueobjects.PersonName;

public record GetProfileByNameQuery(PersonName name) {
}
