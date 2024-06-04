package pe.upc.learningcenter.learning.domain.model.valueobjects;

public record TutorialId(Long tutorialId) {
    public TutorialId {
        if (tutorialId < 0) throw new IllegalArgumentException("tutorialId cannot be negative");
    }

    public TutorialId() {
        this(0L);
    }
}
