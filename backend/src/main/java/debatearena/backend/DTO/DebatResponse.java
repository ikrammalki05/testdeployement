package debatearena.backend.DTO;

import java.time.LocalDateTime;

public class DebatResponse {
    private Long id;
    private SujetResponse sujet;
    private String type;
    private String status; // "EN_COURS", "TERMINE"
    private String choixUtilisateur; // "POUR" ou "CONTRE"
    private LocalDateTime dateDebut;
    private Integer duree;
    private Integer note; // Pour les débats test

    // Constructeur par défaut
    public DebatResponse() {}

    // Constructeur principal
    public DebatResponse(Long id,
                         SujetResponse sujet,
                         String type,
                         String status,
                         String choixUtilisateur,
                         LocalDateTime dateDebut,
                         Integer duree,
                         Integer note) {
        this.id = id;
        this.sujet = sujet;
        this.type = type;
        this.status = status;
        this.choixUtilisateur = choixUtilisateur;
        this.dateDebut = dateDebut;
        this.duree = duree;
        this.note = note;
    }

    public Long getId() {
        return id;
    }

    public SujetResponse getSujet() {
        return sujet;
    }

    public String getType() {
        return type;
    }

    public String getStatus() {
        return status;
    }

    public String getChoixUtilisateur() {
        return choixUtilisateur;
    }

    public LocalDateTime getDateDebut() {
        return dateDebut;
    }

    public Integer getDuree() {
        return duree;
    }

    public Integer getNote() {
        return note;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSujet(SujetResponse sujet) {
        this.sujet = sujet;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setChoixUtilisateur(String choixUtilisateur) {
        this.choixUtilisateur = choixUtilisateur;
    }

    public void setDateDebut(LocalDateTime dateDebut) {
        this.dateDebut = dateDebut;
    }

    public void setDuree(Integer duree) {
        this.duree = duree;
    }

    public void setNote(Integer note) {
        this.note = note;
    }
}