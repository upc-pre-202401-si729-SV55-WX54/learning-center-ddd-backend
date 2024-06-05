package pe.upc.learningcenter.learning.domain.model.aggregates;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.Getter;
import org.apache.logging.log4j.util.Strings;
import pe.upc.learningcenter.learning.domain.model.valueobjects.LearningPath;
import pe.upc.learningcenter.shared.domain.model.aggregate.AuditableAbstractAggregateRoot;

@Entity
@Getter
public class Course extends AuditableAbstractAggregateRoot<Course> {

    private String title;
    private String description;

    /**
     * The learning path for this course
     */
    @Embedded
    private final LearningPath learningPath;

    public Course(){
        this.title = Strings.EMPTY;
        this.description = Strings.EMPTY;
        this.learningPath = new LearningPath();
    }


}
