package debatearena.backend.Controller;

import debatearena.backend.DTO.*;
import debatearena.backend.Service.UtilisateurService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UtilisateurController {

    private final UtilisateurService utilisateurService;

    // ========== PROFIL PERSONNEL ==========

    @GetMapping("/me")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<UtilisateurProfile> getMyProfile() {
        UtilisateurProfile profile = utilisateurService.getMyProfile();
        return ResponseEntity.ok(profile);
    }

    @PutMapping("/me")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<UtilisateurProfile> updateMyProfile(
            @ModelAttribute UpdateProfileRequest request
    ) throws IOException {
        UtilisateurProfile updatedProfile = utilisateurService.updateMyProfile(request);
        return ResponseEntity.ok(updatedProfile);
    }

    @PutMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<UtilisateurProfile> updateMyProfileImage(
            @RequestParam("image") MultipartFile image
    ) throws IOException {
        UtilisateurProfile updatedProfile = utilisateurService.updateProfileImage(image);
        return ResponseEntity.ok(updatedProfile);
    }

    // ========== DASHBOARD ==========

    @GetMapping("/dashboard")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Dashboard> getDashboard() {
        Dashboard dashboard = utilisateurService.getDashboard();
        return ResponseEntity.ok(dashboard);
    }
}