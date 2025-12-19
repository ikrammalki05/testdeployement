package debatearena.backend.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import debatearena.backend.DTO.AuthResponse;
import debatearena.backend.DTO.SignInRequest;
import debatearena.backend.DTO.SignUpRequest;
import debatearena.backend.DTO.SignUpResponse;
import debatearena.backend.Entity.Utilisateur;
import debatearena.backend.Service.BadgeService;
import debatearena.backend.Service.CustomUtilisateurService;
import debatearena.backend.Service.UtilisateurService;
import debatearena.backend.Security.JwtUtil;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthController.class)
@AutoConfigureMockMvc(addFilters = false)
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UtilisateurService utilisateurService;

    @MockBean
    private AuthenticationManager authenticationManager;

    @MockBean
    private BadgeService badgeService;

    @MockBean
    private JwtUtil jwtUtil;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private CustomUtilisateurService customUtilisateurService;

    // -------- SIGNUP SUCCESS ----------
    @Test
    void signup_ShouldReturn200_WhenUserCreated() throws Exception {

        SignUpRequest request = new SignUpRequest();
        request.setEmail("test@gmail.com");
        request.setPassword("123456");

        SignUpResponse response = new SignUpResponse();
        response.setMessage("created");

        when(utilisateurService.findUtilisateurByEmail("test@gmail.com"))
                .thenReturn(Optional.empty());

        when(utilisateurService.signup(any(SignUpRequest.class)))
                .thenReturn(response);

        mockMvc.perform(post("/api/auth/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }

    // -------- SIGNUP USER ALREADY EXISTS ----------
    @Test
    void signup_ShouldReturn400_WhenUserAlreadyExists() throws Exception {

        SignUpRequest request = new SignUpRequest();
        request.setEmail("exist@gmail.com");
        request.setPassword("123456");

        when(utilisateurService.findUtilisateurByEmail("exist@gmail.com"))
                .thenReturn(Optional.of(Mockito.mock(Utilisateur.class)));

        mockMvc.perform(post("/api/auth/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    // -------- SIGNIN SUCCESS ----------
    @Test
    void signin_ShouldReturn200_WhenCredentialsValid() throws Exception {

        SignInRequest request = new SignInRequest();
        request.setEmail("test@gmail.com");
        request.setPassword("123456");

        // Mock Authentication
        Authentication authentication = Mockito.mock(Authentication.class);
        when(authentication.isAuthenticated()).thenReturn(true);

        // Mock AuthenticationManager
        when(authenticationManager.authenticate(any(Authentication.class)))
                .thenReturn(authentication);

        // Mock utilisateurService.signin pour renvoyer le token et le role
        AuthResponse mockResponse = new AuthResponse();
        mockResponse.setToken("token123");
        mockResponse.setRole("USER");  // ou le rôle attendu
        when(utilisateurService.signin(any(SignInRequest.class)))
                .thenReturn(mockResponse);

        mockMvc.perform(post("/api/auth/signin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"token\":\"token123\",\"role\":\"USER\"}"));
    }




    // -------- SIGNIN FAILED ----------
    @Test
    void signin_ShouldReturn401_WhenBadCredentials() throws Exception {

        SignInRequest request = new SignInRequest();
        request.setEmail("wrong@gmail.com");
        request.setPassword("wrong");

        when(authenticationManager.authenticate(any(Authentication.class)))
                .thenThrow(new RuntimeException("Bad credentials"));

        mockMvc.perform(post("/api/auth/signin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isUnauthorized());
    }
}
