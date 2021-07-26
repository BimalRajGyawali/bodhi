package group.bonjai.bodhi.responses;

import org.springframework.http.HttpStatus;

public class AuthenticationResponse extends HttpResponse{
    private String token;
    private String username;
    private String role;

    public AuthenticationResponse(HttpStatus status, String token, String username, String role) {
        super(status);
        this.token = token;
        this.username = username;
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }

    public AuthenticationResponse setToken(String token) {
        this.token = token;
        return this;
    }
}
