package group.bonjai.bodhi.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
public class CreateDepartmentRequest extends HttpRequest {
    @NotBlank(message = "FullName cannot be empty")
    private final String fullName;

    @NotBlank(message = "ShortName cannot be empty")
    private final String shortName;

    @NotBlank(message = "FirstName cannot be blank")
    private final String hodFirstName;

    private final String hodMiddleName;

    @NotBlank(message = "LastName cannot be blank")
    private final String hodLastName;

    @Email(message = "Email should be valid")
    private final String hodEmail;

    private final String hodPhoneNumber;

}
