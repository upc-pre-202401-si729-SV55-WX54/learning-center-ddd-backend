package pe.upc.learningcenter.learning.domain.services;

import pe.upc.learningcenter.learning.domain.model.commands.CreateStudentCommand;

public interface StudentCommandService {
    Long handle(CreateStudentCommand command);
}
