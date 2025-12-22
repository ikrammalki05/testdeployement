package debatearena.backend.DTO;

public class SujetResponse {
    private Long id;
    private String titre;
    private String categorie;
    private String difficulte;
    private boolean accessible;

    // Constructeur
    public SujetResponse(Long id, String titre, String categorie,
                         String difficulte, boolean accessible) {
        this.id = id;
        this.titre = titre;
        this.categorie = categorie;
        this.difficulte = difficulte;
        this.accessible = accessible;
    }

    // Getters seulement (pas de setters = immuable)
    public Long getId() { return id; }
    public String getTitre() { return titre; }
    public String getCategorie() { return categorie; }
    public String getDifficulte() { return difficulte; }
    public boolean isAccessible() { return accessible; }
}