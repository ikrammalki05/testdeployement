package debatearena.backend.DTO;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UpdateProfileRequest {
    private String nom;
    private String prenom;
    private MultipartFile image;

    public UpdateProfileRequest(String nom, String prenom, MultipartFile image) {
        this.nom = nom;
        this.prenom = prenom;
        this.image = image;
    }

    public UpdateProfileRequest() {
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
}
