package debatearena.backend.Integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import debatearena.backend.DTO.SignInRequest;
import debatearena.backend.DTO.SignUpRequest;
import debatearena.backend.Repository.UtilisateurRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")  // ← Active le profil "test" pour utiliser H2
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
        SignUpRequest request = new SignUpRequest();
        request.setNom("Doe");
        request.setPrenom("John");
        request.setEmail("john@test.com");
        request.setPassword("password123");

        mockMvc.perform(post("/api/auth/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("john@test.com"))
                .andExpect(jsonPath("$.nom").value("Doe"))
                .andExpect(jsonPath("$.score").value(0));
    }

    @Test
    void signin_shouldAuthenticate_andReturnToken() throws Exception {
        // 1️⃣ Signup d'abord (données réelles en DB)
        SignUpRequest signup = new SignUpRequest();
        signup.setNom("Doe");
        signup.setPrenom("John");
        signup.setEmail("login@test.com");
        signup.setPassword("password123");

        mockMvc.perform(post("/api/auth/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(signup)));

        // 2️⃣ Signin
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
        SignInRequest signin = new SignInRequest();
        signin.setEmail("unknown@test.com");
        signin.setPassword("wrong");

        mockMvc.perform(post("/api/auth/signin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(signin)))
                .andExpect(status().isUnauthorized());
    }
}
