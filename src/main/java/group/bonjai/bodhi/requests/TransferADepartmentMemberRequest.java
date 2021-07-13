package group.bonjai.bodhi.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class TransferADepartmentMemberRequest extends HttpRequest{
    @NotNull(message = "departmentMemberId cannot be blank")
    private final UUID departmentMemberId;

    @NotNull(message = "newDepartmentId cannot be blank")
    private final UUID newDepartmentId;

    private final String newRole;
}
