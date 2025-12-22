package debatearena.backend.DTO;

import java.time.LocalDateTime;

public class MessageResponse {
    private Long id;
    private String contenu;
    private String auteur; // "UTILISATEUR" ou "CHATBOT"
    private LocalDateTime timestamp;

    public MessageResponse(Long id,
                           String contenu,
                           String auteur,
                           LocalDateTime timestamp) {
        this.id = id;
        this.contenu = contenu;
        this.auteur = auteur;
        this.timestamp = timestamp;
    }

    public MessageResponse() {

    }

    public Long getId() {
        return id;
    }

    public String getContenu() {
        return contenu;
    }

    public String getAuteur() {
        return auteur;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
