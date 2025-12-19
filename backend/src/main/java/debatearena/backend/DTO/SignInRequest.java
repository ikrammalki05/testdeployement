package debatearena.backend.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class SignInRequest {
    private String email;
    private String password;


    // ✔️ Constructeur vide (OBLIGATOIRE pour Spring / Jackson)
    public SignInRequest() {
    }

    // ✔️ Constructeur avec paramètres (utile pour les tests)
    public SignInRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // ✔️ Getters et Setters
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
