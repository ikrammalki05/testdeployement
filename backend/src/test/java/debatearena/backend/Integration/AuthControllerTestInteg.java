package debatearena.backend.Integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import debatearena.backend.DTO.SignInRequest;
import debatearena.backend.Repository.UtilisateurRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

// IMPORTANT : Remplacer 'post' par 'multipart' pour le signup
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class AuthControllerTestInteg {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @BeforeEach
    void setUp() {
        utilisateurRepository.deleteAll();
    }

    @Test
    void signup_shouldCreateUser_andReturn200() throws Exception {
        // CORRECTION : Utilisation de multipart au lieu de JSON pour le Signup
        mockMvc.perform(multipart("/api/auth/signup")
                        .param("nom", "Doe")
                        .param("prenom", "John")
                        .param("email", "john@test.com")
                        .param("password", "password123")
                        .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("john@test.com"))
                .andExpect(jsonPath("$.nom").value("Doe"))
                .andExpect(jsonPath("$.score").value(0));
    }

    @Test
    void signin_shouldAuthenticate_andReturnToken() throws Exception {
        // 1️⃣ Signup d'abord (CORRECTION : multipart ici aussi pour que l'user soit créé)
        mockMvc.perform(multipart("/api/auth/signup")
                        .param("nom", "Doe")
                        .param("prenom", "John")
                        .param("email", "login@test.com")
                        .param("password", "password123")
                        .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isOk()); // On s'assure que la création a réussi

        // 2️⃣ Signin (Celui-ci reste en JSON car le endpoint /signin attend du JSON)
        SignInRequest signin = new SignInRequest();
        signin.setEmail("login@test.com");
        signin.setPassword("password123");

        mockMvc.perform(post("/api/auth/signin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(signin)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").exists())
                .andExpect(jsonPath("$.role").value("UTILISATEUR"));
    }

    @Test
    void signin_shouldFail_withWrongPassword() throws Exception {
        // Ce test reste inchangé car il teste le JSON et l'échec
        SignInRequest signin = new SignInRequest();
        signin.setEmail("unknown@test.com");
        signin.setPassword("wrong");

        mockMvc.perform(post("/api/auth/signin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(signin)))
                .andExpect(status().isUnauthorized());
    }
}