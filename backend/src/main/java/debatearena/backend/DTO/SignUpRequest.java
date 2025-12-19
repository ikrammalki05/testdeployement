package debatearena.backend.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class SignUpRequest {
    private String nom;
    private String prenom;
    private String email;
    private String password;
    // ✔️ Constructeur vide (OBLIGATOIRE pour Spring / Jackson)
    public SignUpRequest() {
    }

    // ✔️ Constructeur avec paramètres (pour tes tests)
    public SignUpRequest(String nom, String prenom, String email, String password) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
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
