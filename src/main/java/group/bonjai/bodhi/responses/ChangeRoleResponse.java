package group.bonjai.bodhi.responses;

import org.springframework.http.HttpStatus;

public class ChangeRoleResponse extends HttpResponse{
    private final String message;

    public ChangeRoleResponse(HttpStatus status) {
        super(status);
        this.message = "Role changed successfully";
    }

    public String getMessage() {
        return message;
    }
}
