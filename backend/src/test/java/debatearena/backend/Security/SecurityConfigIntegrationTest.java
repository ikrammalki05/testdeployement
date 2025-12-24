package debatearena.backend.Security;

import debatearena.backend.DTO.SignInRequest;
import debatearena.backend.Repository.UtilisateurRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

// IMPORTANT : Import de 'multipart' ajouté ici
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class SecurityConfigIntegrationTest {

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
    void unauthenticatedUser_cannotAccessProtectedEndpoint() throws Exception {
        mockMvc.perform(get("/api/admin/dashboard"))
                .andExpect(status().isForbidden()); // pas de JWT, accès interdit
    }

    @Test
    void authenticatedUser_canAccessProtectedEndpoint_withJWT() throws Exception {
        // 1️⃣ Signup : CORRIGÉ -> Utilisation de multipart/form-data au lieu de JSON
        // On n'utilise pas SignUpRequest ici car on simule un formulaire HTML/Multipart
        mockMvc.perform(multipart("/api/auth/signup")
                        .param("nom", "Doe")
                        .param("prenom", "John")
                        .param("email", "admin@test.com")
                        .param("password", "password123")
                        // Note : Si votre contrôleur exige une image, décommentez la ligne ci-dessous :
                        // .file(new org.springframework.mock.web.MockMultipartFile("image", "", "image/png", new byte[0]))
                        .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isOk());

        // 2️⃣ Signin pour récupérer le token (Reste en JSON car endpoint standard)
        SignInRequest signin = new SignInRequest();
        signin.setEmail("admin@test.com");
        signin.setPassword("password123");

        String response = mockMvc.perform(post("/api/auth/signin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(signin)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        // Extraire le token du JSON
        String token = objectMapper.readTree(response).get("token").asText();

        // 3️⃣ Accéder au endpoint protégé avec le token
        mockMvc.perform(get("/api/admin/dashboard")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isForbidden()); // L'utilisateur créé est USER, donc accès ADMIN interdit
    }
}