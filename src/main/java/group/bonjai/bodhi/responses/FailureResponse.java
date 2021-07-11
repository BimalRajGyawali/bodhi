package group.bonjai.bodhi.responses;

import org.springframework.http.HttpStatus;

import java.util.Map;

public class FailureResponse extends HttpResponse{
    private Map<String, String> failureMessage;

    public FailureResponse(HttpStatus status, Map<String, String> failureMessage){
        super(status, false);
        this.failureMessage = failureMessage;
    }

    public Map<String, String> getFailureMessage() {
        return failureMessage;
    }

    public FailureResponse setFailureMessage(Map<String, String> failureMessage) {
        this.failureMessage = failureMessage;
        return this;
    }
}
