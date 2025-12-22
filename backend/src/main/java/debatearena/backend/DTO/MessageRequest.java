package debatearena.backend.DTO;

public class MessageRequest {
    private String contenu;

    // Constructeur par défaut
    public MessageRequest() {}

    public MessageRequest(String contenu) { this.contenu = contenu; }

    // Getters et Setters
    public String getContenu() { return contenu; }
    public void setContenu(String contenu) { this.contenu = contenu; }

    public boolean isValid() {
        return contenu != null && !contenu.trim().isEmpty();
    }
}