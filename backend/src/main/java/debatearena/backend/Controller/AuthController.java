package debatearena.backend.Controller;

import debatearena.backend.DTO.AuthResponse;
import debatearena.backend.DTO.SignInRequest;
import debatearena.backend.DTO.SignUpRequest;
import debatearena.backend.DTO.SignUpResponse;
import debatearena.backend.Service.AuthService;
import debatearena.backend.Service.PasswordResetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth/")
public class AuthController {

    private final AuthService authService;
    private final PasswordResetService passwordResetService;

    public AuthController(AuthService authService, PasswordResetService passwordResetService) {
        this.authService = authService;
        this.passwordResetService = passwordResetService;
    }

    @PostMapping(value = "/signup", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> signup(@ModelAttribute SignUpRequest signUpRequest) {
        try {
            SignUpResponse response = authService.signup(signUpRequest);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            // Capture l'erreur "Email déjà utilisé" envoyée par le Service (ou le Mock)
            // Renvoie une erreur 400 (Bad Request) au lieu de planter
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody SignInRequest signInRequest) {
        try {
            AuthResponse response = authService.signin(signInRequest);
            return ResponseEntity.ok(response);
        } catch (BadCredentialsException e) {
            // Capture l'erreur "Mauvais mot de passe"
            // Renvoie une erreur 401 (Unauthorized)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Email ou mot de passe incorrect");
        } catch (Exception e) {
            // Sécurité pour toute autre erreur imprévue
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur interne");
        }
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestParam String email) {
        try {
            passwordResetService.createPasswordResetToken(email);
            return ResponseEntity.ok("Email envoyé si l'utilisateur existe");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestParam String token, @RequestParam String newPassword) {
        try {
            passwordResetService.resetPassword(token, newPassword);
            return ResponseEntity.ok("Mot de passe mis à jour");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
//package debatearena.backend.Controller;
//
//import debatearena.backend.DTO.AuthResponse;
//import debatearena.backend.DTO.SignInRequest;
//import debatearena.backend.DTO.SignUpRequest;
//import debatearena.backend.DTO.SignUpResponse;
//import debatearena.backend.Security.JwtUtil;
//import debatearena.backend.Service.AuthService;
//import debatearena.backend.Service.BadgeService;
//import debatearena.backend.Service.PasswordResetService;
//import debatearena.backend.Service.UtilisateurService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.web.bind.annotation.*;
//
//import java.io.IOException;
//
//@RestController
//@RequestMapping("/api/auth/")
//public class AuthController {
//
//    private final AuthService authService;
//    private final PasswordResetService passwordResetService;
//
//    public AuthController(AuthService authService, PasswordResetService passwordResetService) {
//        this.authService = authService;
//        this.passwordResetService = passwordResetService;
//    }
//
//    @PostMapping(value = "/signup", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public ResponseEntity<?> signup(@ModelAttribute SignUpRequest signUpRequest ) {
//
//        SignUpResponse response = authService.signup(signUpRequest);
//        return ResponseEntity.ok(response);
//    }
//
//    @PostMapping("/signin")
//    public ResponseEntity<?> signin(@RequestBody SignInRequest signInRequest ) {
//
//        AuthResponse response = authService.signin(signInRequest);
//        return ResponseEntity.ok(response);
//    }
//
//    @PostMapping("/forgot-password")
//    public ResponseEntity<String> forgotPassword(@RequestParam String email) {
//
//        passwordResetService.createPasswordResetToken(email);
//        return ResponseEntity.ok("Email envoyé si l'utilisateur existe");
//    }
//
//    @PostMapping("/reset-password")
//    public ResponseEntity<String> resetPassword(@RequestParam String token, @RequestParam String newPassword) {
//
//        passwordResetService.resetPassword(token, newPassword);
//        return ResponseEntity.ok("Mot de passe mis à jour");
//    }
//
//}

