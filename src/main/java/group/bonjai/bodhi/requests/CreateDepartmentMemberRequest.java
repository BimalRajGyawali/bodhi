package group.bonjai.bodhi.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class CreateDepartmentMemberRequest extends HttpRequest{
    @NotBlank(message = "firstName cannot be empty")
    private final String firstName;

    private final String middleName;

    @NotBlank(message = "lastName cannot be empty")
    private final String lastName;

    @Email(message = "email must be valid")
    @NotBlank(message = "email cannot be empty")
    private final String email;

    @NotBlank(message = "phoneNumber cannot be empty")
    private final String phoneNumber;

    @NotBlank(message = "role cannot be empty")
    private final String role;

    @NotNull(message = "departmentId cannot be blank")
    private final UUID departmentId;
}
