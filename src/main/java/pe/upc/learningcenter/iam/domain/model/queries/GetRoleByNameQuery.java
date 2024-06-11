package pe.upc.learningcenter.iam.domain.model.queries;

import pe.upc.learningcenter.iam.domain.model.valueobjects.Roles;

public record GetRoleByNameQuery(Roles name) {
}
