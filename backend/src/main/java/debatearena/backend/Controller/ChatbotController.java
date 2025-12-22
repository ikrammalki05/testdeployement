package debatearena.backend.Controller;

import debatearena.backend.Service.DebatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/chatbot")
public class ChatbotController {

    private final DebatService debatService;

    public ChatbotController(DebatService debatService) {
        this.debatService = debatService;
    }

    // Vérifier l'état du chatbot
    @GetMapping("/health")
    public ResponseEntity<Map<String, Object>> getChatbotHealth() {
        Map<String, Object> status = debatService.getChatbotStatus();
        return ResponseEntity.ok(status);
    }

    // Tester directement le chatbot (pour debug)
    @PostMapping("/test")
    public ResponseEntity<Map<String, Object>> testChatbot(@RequestBody Map<String, String> request) {
        String message = request.get("message");
        String result = debatService.testerChatbot(message);

        return ResponseEntity.ok(Map.of(
                "test_result", result,
                "timestamp", java.time.LocalDateTime.now()
        ));
    }
}
