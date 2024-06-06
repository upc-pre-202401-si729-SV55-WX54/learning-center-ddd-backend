package pe.upc.learningcenter.profiles.interfaces.rest;


import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.upc.learningcenter.profiles.domain.model.commands.CreateProfileCommand;
import pe.upc.learningcenter.profiles.domain.model.queries.GetProfileByIdQuery;
import pe.upc.learningcenter.profiles.domain.services.ProfileCommandService;
import pe.upc.learningcenter.profiles.domain.services.ProfileQueryService;
import pe.upc.learningcenter.profiles.interfaces.rest.resources.CreateProfileResource;
import pe.upc.learningcenter.profiles.interfaces.rest.resources.ProfileResource;
import pe.upc.learningcenter.profiles.interfaces.rest.transform.CreateProfileCommandFromResourceAssembler;
import pe.upc.learningcenter.profiles.interfaces.rest.transform.ProfileResourceFromEntityAssembler;

@RestController
@RequestMapping(value = "/api/v2/profiles", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name="Profile", description = "Profile management endpoints")
public class ProfileController {

    private final ProfileQueryService profileQueryService;
    private final ProfileCommandService profileCommandService;

    public ProfileController(ProfileQueryService profileQueryService, ProfileCommandService profileCommandService) {
        this.profileQueryService = profileQueryService;
        this.profileCommandService = profileCommandService;
    }


    @PostMapping
    public ResponseEntity<ProfileResource> createProfile(@RequestBody CreateProfileResource createProfileResource) {

        var createProfileCommand = CreateProfileCommandFromResourceAssembler.toCommandFromResource(createProfileResource);
        var profile = profileCommandService.handle(createProfileCommand);

        if (profile.isEmpty()) return ResponseEntity.badRequest().build();

        var profileResource = ProfileResourceFromEntityAssembler.toResourceFromEntity(profile.get());
        return ResponseEntity.ok(profileResource);

    }

    @GetMapping("/{profileId}")
    public ResponseEntity<ProfileResource> getProfileById(@PathVariable Long profileId) {
        var getProfileByIdQuery = new GetProfileByIdQuery(profileId);
        var profile = profileQueryService.handle(getProfileByIdQuery);
        if (profile.isEmpty()) return ResponseEntity.badRequest().build();
        var profileResource = ProfileResourceFromEntityAssembler.toResourceFromEntity(profile.get());
        return ResponseEntity.ok(profileResource);
    }

}
