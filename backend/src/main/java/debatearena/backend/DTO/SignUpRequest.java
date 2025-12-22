package debatearena.backend.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class SignUpRequest {
    private String nom;
    private String prenom;
    private String email;
    private String password;
    private MultipartFile image;
    // ✔️ Constructeur vide (OBLIGATOIRE pour Spring / Jackson)
    public SignUpRequest() {
    }

    // ✔️ Constructeur avec paramètres (pour tes tests)
    public SignUpRequest(String nom, String prenom, String email, String password, MultipartFile image) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.image = image;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    // ✔️ Getters et Setters (à écrire si pas Lombok)
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }



}
