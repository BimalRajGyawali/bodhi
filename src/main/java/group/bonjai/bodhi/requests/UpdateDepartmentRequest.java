package group.bonjai.bodhi.requests;


import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class UpdateDepartmentRequest extends HttpRequest{
    @NotNull(message = "UUID cannot be blank")
    private final UUID id;
    private final String newFullName;
    private final String newShortName;
}
