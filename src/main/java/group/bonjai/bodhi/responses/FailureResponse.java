package group.bonjai.bodhi.responses;

import org.springframework.http.HttpStatus;

import java.util.Map;

public class FailureResponse extends HttpResponse{
    private Map<String, String> message;

    public FailureResponse(HttpStatus status, Map<String, String> message){
        super(status, false);
        this.message = message;
    }

    public Map<String, String> getMessage() {
        return message;
    }

    public FailureResponse setMessage(Map<String, String> message) {
        this.message = message;
        return this;
    }
}
