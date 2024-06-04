package pe.upc.learningcenter.learning.domain.model.valueobjects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.OneToMany;
import pe.upc.learningcenter.learning.domain.model.entities.LearningPathItem;

import java.util.List;

@Embeddable
public class LearningPath {

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<LearningPathItem> learningPathItems;

}
