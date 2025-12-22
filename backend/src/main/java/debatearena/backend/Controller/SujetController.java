package debatearena.backend.Controller;


import debatearena.backend.DTO.SujetResponse;
import debatearena.backend.Service.SujetService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sujets")
public class SujetController {

    private final SujetService sujetService;

    public SujetController(SujetService sujetService) {
        this.sujetService = sujetService;
    }

    // ========== ENDPOINTS PUBLICS (sans auth) ==========

    /**
     * GET /api/sujets
     * Récupère tous les sujets (avec indication d'accessibilité)
     * Nécessite d'être connecté pour connaître le niveau
     */
    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<SujetResponse>> getAllSujets() {
        List<SujetResponse> sujets = sujetService.getAllSujets();
        return ResponseEntity.ok(sujets);
    }

    /**
     * GET /api/sujets/{id}
     * Récupère un sujet spécifique
     */
    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<SujetResponse> getSujetById(@PathVariable Long id) {
        SujetResponse sujet = sujetService.getSujetById(id);
        return ResponseEntity.ok(sujet);
    }

    /**
     * GET /api/sujets/filtrer
     * Filtre les sujets par catégorie et/ou difficulté
     * Exemples:
     *   /api/sujets/filtrer?categorie=ART
     *   /api/sujets/filtrer?difficulte=DEBUTANT
     *   /api/sujets/filtrer?categorie=ART&difficulte=INTERMEDIAIRE
     */
    @GetMapping("/filtrer")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<SujetResponse>> filtrerSujets(
            @RequestParam(required = false) String categorie,
            @RequestParam(required = false) String difficulte) {

        List<SujetResponse> sujets = sujetService.getSujetsFiltres(categorie, difficulte);
        return ResponseEntity.ok(sujets);
    }

    /**
     * GET /api/sujets/rechercher
     * Recherche des sujets par titre (insensible à la casse)
     */
    @GetMapping("/rechercher")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<SujetResponse>> rechercherSujets(@RequestParam String q) {
        List<SujetResponse> sujets = sujetService.searchSujets(q);
        return ResponseEntity.ok(sujets);
    }

    /**
     * GET /api/sujets/recommandes
     * Sujets recommandés (uniquement ceux accessibles au niveau de l'utilisateur)
     */
    @GetMapping("/recommandes")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<SujetResponse>> getSujetsRecommandes() {
        List<SujetResponse> sujets = sujetService.getSujetsRecommandes();
        return ResponseEntity.ok(sujets);
    }

    /**
     * GET /api/sujets/categories
     * Liste toutes les catégories disponibles
     * Public - pas besoin d'être connecté
     */
    @GetMapping("/categories")
    public ResponseEntity<List<String>> getAllCategories() {
        List<String> categories = sujetService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    /**
     * GET /api/sujets/difficultes
     * Liste toutes les difficultés disponibles
     * Public - pas besoin d'être connecté
     */
    @GetMapping("/difficultes")
    public ResponseEntity<List<String>> getAllDifficultes() {
        List<String> difficultes = sujetService.getAllDifficultes();
        return ResponseEntity.ok(difficultes);
    }
}
