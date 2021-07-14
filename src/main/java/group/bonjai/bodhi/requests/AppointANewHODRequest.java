package group.bonjai.bodhi.requests;

import group.bonjai.bodhi.requests.HttpRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class AppointANewHODRequest extends HttpRequest {

    @NotNull(message = "newHODId cannot be blank")
    private final UUID newHODId;

    @NotBlank(message = "You must provide the new role of old HOD")
    private final String newRoleOfOldHOD;
}
