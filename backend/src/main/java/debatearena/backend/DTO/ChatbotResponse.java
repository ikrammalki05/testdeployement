package debatearena.backend.DTO;

public class ChatbotResponse {
    private String response;
    private String session_id;

    public ChatbotResponse() {
    }

    public ChatbotResponse(String response, String session_id) {
        this.response = response;
        this.session_id = session_id;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getSession_id() {
        return session_id;
    }

    public void setSession_id(String session_id) {
        this.session_id = session_id;
    }
}