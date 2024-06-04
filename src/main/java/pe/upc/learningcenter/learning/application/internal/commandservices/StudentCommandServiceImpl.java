package pe.upc.learningcenter.learning.application.internal.commandservices;

import org.springframework.stereotype.Service;
import pe.upc.learningcenter.learning.application.internal.outboundservices.acl.ExternalProfileService;
import pe.upc.learningcenter.learning.domain.model.aggregates.Student;
import pe.upc.learningcenter.learning.domain.model.commands.CreateStudentCommand;
import pe.upc.learningcenter.learning.domain.model.services.StudentCommandService;
import pe.upc.learningcenter.learning.domain.model.valueobjects.AcmeStudentRecordId;
import pe.upc.learningcenter.learning.infrastructure.persistence.jpa.repositories.StudentRepository;

@Service
public class StudentCommandServiceImpl implements StudentCommandService {

    private final StudentRepository studentRepository;
    private final ExternalProfileService externalProfileService;

    public StudentCommandServiceImpl(StudentRepository studentRepository, ExternalProfileService externalProfileService) {
        this.studentRepository = studentRepository;
        this.externalProfileService = externalProfileService;
    }

    @Override
    public AcmeStudentRecordId handle(CreateStudentCommand command) {

        var profileId = externalProfileService.fetchProfileIdByEmail(command.email());

        if (profileId.isEmpty()) {
            profileId = externalProfileService.createProfile(command.firstName(),
                    command.lastName(),
                    command.email(),
                    command.street(),
                    command.number(),
                    command.city(),
                    command.postalCode(),
                    command.country());
        } else {
            studentRepository.findByProfileId(profileId.get()).ifPresent(student -> {
                throw new IllegalArgumentException("Student already exists");
            });
        }

        if (profileId.isEmpty()) throw new IllegalArgumentException("Unable to create profile");

        var student = new Student(profileId.get());
        studentRepository.save(student);

        return student.getAcmeStudentRecordId();
    }
}
