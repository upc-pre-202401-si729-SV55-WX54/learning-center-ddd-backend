package pe.upc.learningcenter.learning.domain.services;

import pe.upc.learningcenter.learning.domain.model.commands.CreateStudentCommand;
import pe.upc.learningcenter.learning.domain.model.valueobjects.AcmeStudentRecordId;

public interface StudentCommandService {
    AcmeStudentRecordId handle(CreateStudentCommand command);
}
