package debatearena.backend.DTO;

import java.time.LocalDateTime;

public class DebatRecap {
    private Long id;
    private String sujet;
    private String categorie;
    private String difficulte;
    private String type;
    private String choixUtilisateur;
    private Integer note;
    private LocalDateTime date;
    private String duree; // Format "HH:mm:ss"

    // Constructeur par défaut
    public DebatRecap() {}

    // Constructeur principal
    public DebatRecap(Long id,
                      String sujet,
                      String categorie,
                      String difficulte,
                      String type,
                      String choixUtilisateur,
                      Integer note,
                      LocalDateTime date,
                      String duree) {
        this.id = id;
        this.sujet = sujet;
        this.categorie = categorie;
        this.difficulte = difficulte;
        this.type = type;
        this.choixUtilisateur = choixUtilisateur;
        this.note = note;
        this.date = date;
        this.duree = duree;
    }

    // Getters seulement
    public Long getId() { return id; }
    public String getSujet() { return sujet; }
    public String getCategorie() { return categorie; }
    public String getDifficulte() { return difficulte; }
    public String getType() { return type; }
    public String getChoixUtilisateur() { return choixUtilisateur; }
    public Integer getNote() { return note; }
    public LocalDateTime getDate() { return date; }
    public String getDuree() { return duree; }
}