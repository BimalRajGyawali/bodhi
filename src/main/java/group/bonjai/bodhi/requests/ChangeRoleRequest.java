package group.bonjai.bodhi.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class ChangeRoleRequest extends HttpRequest{
    @NotNull(message = "departmentMemberId cannot be blank")
    private final UUID departmentMemberId;

    @NotBlank(message = "newRole cannot be blank")
    private final String newRole;
}
