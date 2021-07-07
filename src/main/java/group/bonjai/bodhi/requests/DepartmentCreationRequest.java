package group.bonjai.bodhi.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DepartmentCreationRequest extends HttpRequest{
    private final String fullName;
    private final String shortName;
    private final String hodEmail;
    private final String hodPhoneNumber;

}
