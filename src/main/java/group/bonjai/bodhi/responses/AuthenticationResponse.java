package group.bonjai.bodhi.responses;

import org.springframework.http.HttpStatus;

public class AuthenticationResponse extends HttpResponse{
    private String token;

    public AuthenticationResponse(HttpStatus status, String token) {
        super(status);
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public AuthenticationResponse setToken(String token) {
        this.token = token;
        return this;
    }
}
