package pe.upc.learningcenter.learning.application.internal.commandservices;

import org.springframework.stereotype.Service;
import pe.upc.learningcenter.learning.application.internal.outboudservices.acl.ExternalProfileService;
import pe.upc.learningcenter.learning.domain.model.commands.CreateStudentCommand;
import pe.upc.learningcenter.learning.domain.services.StudentCommandService;

@Service
public class StudentCommandServiceImpl implements StudentCommandService {

    private final ExternalProfileService externalProfileService;

    public StudentCommandServiceImpl(ExternalProfileService externalProfileService) {
        this.externalProfileService = externalProfileService;
    }

    @Override
    public Long handle(CreateStudentCommand command) {
        var profileId = externalProfileService.fetchProfileIdByEmail(command.email());
        /***
         * logica de creacion de el estudiante
         */
        //profileId = externalProfileService.createProfile(command.firstName(), command.lastName(), .........);
        return 0L;
    }
}
