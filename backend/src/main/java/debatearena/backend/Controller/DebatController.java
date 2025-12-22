package debatearena.backend.Controller;

import debatearena.backend.DTO.*;
import debatearena.backend.Service.DebatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/debats")
public class DebatController {

    private final DebatService debatService;

    public DebatController(DebatService debatService) {
        this.debatService = debatService;
    }

    // Créer un nouveau débat
    @PostMapping
    public ResponseEntity<DebatResponse> creerDebat(@RequestBody CreerDebatRequest request) {
        DebatResponse response = debatService.creerDebat(request);
        return ResponseEntity.ok(response);
    }

    // Envoyer un message dans un débat
    @PostMapping("/{debatId}/messages")
    public ResponseEntity<MessageResponse> envoyerMessage(
            @PathVariable Long debatId,
            @RequestBody MessageRequest request) {
        MessageResponse response = debatService.envoyerMessage(debatId, request);
        return ResponseEntity.ok(response);
    }

    // Terminer un débat
    @PostMapping("/{debatId}/terminer")
    public ResponseEntity<DebatResponse> terminerDebat(@PathVariable Long debatId) {
        DebatResponse response = debatService.terminerDebat(debatId);
        return ResponseEntity.ok(response);
    }

    // Récupérer un débat spécifique
    @GetMapping("/{debatId}")
    public ResponseEntity<DebatResponse> getDebat(@PathVariable Long debatId) {
        DebatResponse response = debatService.getDebat(debatId);
        return ResponseEntity.ok(response);
    }

    // Récupérer les messages d'un débat
    @GetMapping("/{debatId}/messages")
    public ResponseEntity<List<MessageResponse>> getMessagesDebat(@PathVariable Long debatId) {
        List<MessageResponse> responses = debatService.getMessagesDebat(debatId);
        return ResponseEntity.ok(responses);
    }

    // Récupérer mes débats
    @GetMapping("/mes-debats")
    public ResponseEntity<List<DebatResponse>> getMesDebats() {
        List<DebatResponse> responses = debatService.getMesDebats();
        return ResponseEntity.ok(responses);
    }

    // Récupérer les débats en cours
    @GetMapping("/en-cours")
    public ResponseEntity<List<DebatResponse>> getDebatsEnCours() {
        List<DebatResponse> responses = debatService.getDebatsEnCours();
        return ResponseEntity.ok(responses);
    }

    // Récupérer les débats terminés
    @GetMapping("/termines")
    public ResponseEntity<List<DebatResponse>> getDebatsTermines() {
        List<DebatResponse> responses = debatService.getDebatsTermines();
        return ResponseEntity.ok(responses);
    }

    // Annuler un débat
    @DeleteMapping("/{debatId}")
    public ResponseEntity<Void> annulerDebat(@PathVariable Long debatId) {
        debatService.annulerDebat(debatId);
        return ResponseEntity.noContent().build();
    }

    // Évaluer un test (si tu as cette méthode)
    @PostMapping("/{debatId}/evaluation")
    public ResponseEntity<MessageResponse> evaluerTest(@PathVariable Long debatId) {
        MessageResponse response = debatService.evaluerTest(debatId);
        return ResponseEntity.ok(response);
    }
}