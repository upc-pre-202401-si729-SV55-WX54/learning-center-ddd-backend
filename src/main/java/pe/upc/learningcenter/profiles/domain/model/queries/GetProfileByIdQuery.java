package pe.upc.learningcenter.profiles.domain.model.queries;

public record GetProfileByIdQuery(Long id){
    public GetProfileByIdQuery {
        if (id == null) {
            throw new IllegalArgumentException("id cannot be null");
        }
        if (id < 0) {
            throw new IllegalArgumentException("id cannot be negative");
        }
    }
}
