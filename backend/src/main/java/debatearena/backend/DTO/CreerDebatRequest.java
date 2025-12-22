package debatearena.backend.DTO;

public class CreerDebatRequest {
    private Long sujetId;
    private String type; // "ENTRAINEMENT" ou "TEST"
    private String choix; // "POUR" ou "CONTRE"

    // Constructeur par défaut (NÉCESSAIRE pour Jackson)
    public CreerDebatRequest() {}

    public CreerDebatRequest(Long sujetId, String type, String choix) {
        this.sujetId = sujetId;
        this.type = type;
        this.choix = choix;
    }

    // Getters et Setters seulement
    public Long getSujetId() { return sujetId; }
    public void setSujetId(Long sujetId) { this.sujetId = sujetId; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getChoix() { return choix; }
    public void setChoix(String choix) { this.choix = choix; }

    public boolean isValid() {
        return sujetId != null && sujetId > 0 &&
                type != null && (type.equals("ENTRAINEMENT") || type.equals("TEST")) &&
                choix != null && (choix.equals("POUR") || choix.equals("CONTRE"));
    }
}