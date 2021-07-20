package group.bonjai.bodhi.models;

import lombok.Data;

@Data
public class User {
    private final String email;
    private final String role;
}
