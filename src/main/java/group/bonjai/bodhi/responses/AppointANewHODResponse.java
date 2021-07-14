package group.bonjai.bodhi.responses;

import group.bonjai.bodhi.models.DepartmentMember;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.UUID;

public class AppointANewHODResponse extends HttpResponse{

    private final HOD newHOD;

    @Data
    @Builder
    static final class HOD{
        private final UUID id;
        private final String firstName;
        private final String middleName;
        private final String lastName;
        private final String role;
        private final String email;
    }

    public AppointANewHODResponse(HttpStatus status, DepartmentMember newHOD) {
        super(status);
        this.newHOD = HOD.builder()
                .id(newHOD.getId())
                .firstName(newHOD.getFirstName())
                .middleName(newHOD.getMiddleName())
                .lastName(newHOD.getLastName())
               .email(newHOD.getEmail())
                .role(DepartmentMember.HOD)
               .build();

    }

    public HOD getNewHOD() {
        return newHOD;
    }
}
