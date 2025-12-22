package debatearena.backend.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
//@AllArgsConstructor
public class SignUpResponse {
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private Integer score;
    private String badgeNom;
    private String badgeCategorie;
    private String imageUrl;

    public SignUpResponse(Long id,
                          String nom,
                          String prenom,
                          String email,
                          Integer score,
                          String badgeNom,
                          String badgeCategorie,
                          String imageUrl) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.score = score;
        this.badgeNom = badgeNom;
        this.badgeCategorie = badgeCategorie;
        this.imageUrl = imageUrl;
    }

    public SignUpResponse() {

    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    // ---------- GETTERS ----------
    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

    public Integer getScore() {
        return score;
    }

    public String getBadgeNom() {
        return badgeNom;
    }

    public String getBadgeCategorie() {
        return badgeCategorie;
    }

    // ---------- SETTERS ----------
    public void setId(Long id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public void setBadgeNom(String badgeNom) {
        this.badgeNom = badgeNom;
    }

    public void setBadgeCategorie(String badgeCategorie) {
        this.badgeCategorie = badgeCategorie;
    }

    public void setMessage(String created) {
        // Aucun attribut message dans la classe
    }
}