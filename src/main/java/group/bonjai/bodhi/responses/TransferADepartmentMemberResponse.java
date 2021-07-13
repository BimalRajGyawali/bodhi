package group.bonjai.bodhi.responses;

import group.bonjai.bodhi.models.DepartmentMember;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.UUID;

public class TransferADepartmentMemberResponse extends HttpResponse{
    private final ResponseDepartmentMember departmentMember;

    @Data
    @Builder
    final static class ResponseDepartmentMember{
        private final UUID id;
        private final String firstName;
        private final String middleName;
        private final String lastName;
        private final String role;
        private final ResponseDepartment department;
    }
    @Data
    @Builder
    final static class ResponseDepartment{
        private final UUID id;
        private final String fullName;
        private final String shortName;
    }

    public TransferADepartmentMemberResponse(HttpStatus status, DepartmentMember departmentMember) {
        super(status);
        ResponseDepartment responseDepartment = ResponseDepartment.builder()
                .id(departmentMember.getDepartment().getId())
                .fullName(departmentMember.getDepartment().getFullName())
                .shortName(departmentMember.getDepartment().getShortName())
                .build();

        this.departmentMember = ResponseDepartmentMember.builder()
                .id(departmentMember.getId())
                .firstName(departmentMember.getFirstName())
                .middleName(departmentMember.getMiddleName())
                .lastName(departmentMember.getLastName())
                .role(departmentMember.getRole())
                .department(responseDepartment)
                .build();
    }

    public ResponseDepartmentMember getDepartmentMember() {
        return departmentMember;
    }
}
