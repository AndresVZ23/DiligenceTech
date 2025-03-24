package com.deltatech.diligencetech.platform.profiles.interfaces.rest;

import com.deltatech.diligencetech.platform.profiles.domain.model.queries.GetProfileByAgentIdQuery;
import com.deltatech.diligencetech.platform.profiles.domain.services.ProfileQueryService;
import com.deltatech.diligencetech.platform.profiles.interfaces.rest.resources.ProfileResource;
import com.deltatech.diligencetech.platform.profiles.interfaces.rest.transform.ProfileResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/profiles", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Profiles", description = "Profiles Management Endpoints")
public class ProfilesController {
    private final ProfileQueryService profileQueryService;

    public ProfilesController(ProfileQueryService profileQueryService) {
        this.profileQueryService = profileQueryService;
    }

    @GetMapping("/get/{agentId}")
    public ResponseEntity<ProfileResource> getProfileByAgentId(@PathVariable Long agentId) {
        var getProfileByAgentIdQuery = new GetProfileByAgentIdQuery(agentId);
        var profile = profileQueryService.handle(getProfileByAgentIdQuery);
        if (profile.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var profileResource = ProfileResourceFromEntityAssembler.toResourceFromEntity(profile.get());
        return ResponseEntity.ok(profileResource);
    }
}
