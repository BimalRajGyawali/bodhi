package group.bonjai.bodhi.requests;

import group.bonjai.bodhi.controllers.CreateDepartmentController;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
public class CreateDepartmentRequest extends HttpRequest {

    public CreateDepartmentRequest(){

    }
    @NotBlank(message = "FullName cannot be empty")
    private  String fullName;

    @NotBlank(message = "ShortName cannot be empty")
    private  String shortName;

    @NotBlank(message = "FirstName cannot be blank")
    private  String hodFirstName;

    private  String hodMiddleName;

    @NotBlank(message = "LastName cannot be blank")
    private  String hodLastName;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email cannot be blank")
    private  String hodEmail;

    @NotBlank(message = "PhoneNumber cannot be blank")
    private  String hodPhoneNumber;

}
