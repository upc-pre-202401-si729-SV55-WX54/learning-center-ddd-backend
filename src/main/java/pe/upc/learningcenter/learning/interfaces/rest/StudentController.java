package pe.upc.learningcenter.learning.interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.upc.learningcenter.learning.domain.model.queries.GetStudentByAcmeStudentRecordIdQuery;
import pe.upc.learningcenter.learning.domain.services.StudentCommandService;
import pe.upc.learningcenter.learning.domain.services.StudentQueryService;
import pe.upc.learningcenter.learning.interfaces.rest.resources.CreateStudentResource;
import pe.upc.learningcenter.learning.interfaces.rest.resources.StudentResource;
import pe.upc.learningcenter.learning.interfaces.rest.transform.CreateStudentCommandFromResourceAssembler;
import pe.upc.learningcenter.learning.interfaces.rest.transform.StudentResourceFromEntityAssembler;

@RestController
@RequestMapping(value = "/api/v1/students", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name="Student", description = "Student Management Endpoints")
public class StudentController {

    private final StudentCommandService studentCommandService;
    private final StudentQueryService studentQueryService;

    public StudentController(StudentCommandService studentCommandService, StudentQueryService studentQueryService) {
        this.studentCommandService = studentCommandService;
        this.studentQueryService = studentQueryService;
    }

    /**
     * POST /api/v1/students
     *
     */
    @PostMapping
    public ResponseEntity<StudentResource> createStudent(@RequestBody CreateStudentResource resource){
        var createStudentCommand = CreateStudentCommandFromResourceAssembler.toCommandFromResource(resource);
        var studentId = studentCommandService.handle(createStudentCommand);

        if(studentId.studentRecordId().isEmpty()){
            return ResponseEntity.badRequest().build();
        }

        var getStudentByAcmeStudentRecordIdQuery = new GetStudentByAcmeStudentRecordIdQuery(studentId);
        var student = studentQueryService.handle(getStudentByAcmeStudentRecordIdQuery);
        if(student.isEmpty()){
            return ResponseEntity.badRequest().build();
        }

        var studentResource = StudentResourceFromEntityAssembler.toResourceFromEntity(student.get());

        return new ResponseEntity<>(studentResource, HttpStatus.CREATED);

    }

}
