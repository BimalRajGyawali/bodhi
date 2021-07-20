package group.bonjai.bodhi.requests;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateDepartmentNoticeRequest extends HttpRequest{
    @NotBlank(message = "title cannot be blank")
    private  String title;

    @NotBlank(message = "content cannot be blank")
    private  String content;

    @NotNull(message = "departmentId cannot be blank")
    private  UUID departmentId;
}
