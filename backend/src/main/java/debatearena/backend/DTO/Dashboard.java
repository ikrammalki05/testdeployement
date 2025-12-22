package debatearena.backend.DTO;

import debatearena.backend.Entity.Badge;
import lombok.Data;

import java.util.List;

@Data
public class Dashboard {
    // Niveau et progression
    private String niveau;
    private Double progressionPourcentage;
    private Integer score;
    private Integer pointsPourNiveauSuivant;

    // Badge
    private Badge badgeActuel;

    // Statistiques
    private Integer totalDebats;
    private Integer debatsGagnes;
    private Double tauxReussite;
    private Integer moyenneNotes;
    private Integer meilleureNote;

    // Historique récent
    private List<DebatRecap> debatsRecents;

    public Dashboard(String niveau,
                     Double progressionPourcentage,
                     Integer score,
                     Integer pointsPourNiveauSuivant,
                     Badge badgeActuel,
                     Integer totalDebats,
                     Integer debatsGagnes,
                     Double tauxReussite,
                     Integer moyenneNotes,
                     Integer meilleureNote,
                     List<DebatRecap> debatsRecents) {
        this.niveau = niveau;
        this.progressionPourcentage = progressionPourcentage;
        this.score = score;
        this.pointsPourNiveauSuivant = pointsPourNiveauSuivant;
        this.badgeActuel = badgeActuel;
        this.totalDebats = totalDebats;
        this.debatsGagnes = debatsGagnes;
        this.tauxReussite = tauxReussite;
        this.moyenneNotes = moyenneNotes;
        this.meilleureNote = meilleureNote;
        this.debatsRecents = debatsRecents;
    }

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    public Double getProgressionPourcentage() {
        return progressionPourcentage;
    }

    public void setProgressionPourcentage(Double progressionPourcentage) {
        this.progressionPourcentage = progressionPourcentage;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getPointsPourNiveauSuivant() {
        return pointsPourNiveauSuivant;
    }

    public void setPointsPourNiveauSuivant(Integer pointsPourNiveauSuivant) {
        this.pointsPourNiveauSuivant = pointsPourNiveauSuivant;
    }

    public Badge getBadgeActuel() {
        return badgeActuel;
    }

    public void setBadgeActuel(Badge badgeActuel) {
        this.badgeActuel = badgeActuel;
    }

    public Integer getTotalDebats() {
        return totalDebats;
    }

    public void setTotalDebats(Integer totalDebats) {
        this.totalDebats = totalDebats;
    }

    public Integer getDebatsGagnes() {
        return debatsGagnes;
    }

    public void setDebatsGagnes(Integer debatsGagnes) {
        this.debatsGagnes = debatsGagnes;
    }

    public Double getTauxReussite() {
        return tauxReussite;
    }

    public void setTauxReussite(Double tauxReussite) {
        this.tauxReussite = tauxReussite;
    }

    public Integer getMoyenneNotes() {
        return moyenneNotes;
    }

    public void setMoyenneNotes(Integer moyenneNotes) {
        this.moyenneNotes = moyenneNotes;
    }

    public Integer getMeilleureNote() {
        return meilleureNote;
    }

    public void setMeilleureNote(Integer meilleureNote) {
        this.meilleureNote = meilleureNote;
    }

    public List<DebatRecap> getDebatsRecents() {
        return debatsRecents;
    }

    public void setDebatsRecents(List<DebatRecap> debatsRecents) {
        this.debatsRecents = debatsRecents;
    }
}
