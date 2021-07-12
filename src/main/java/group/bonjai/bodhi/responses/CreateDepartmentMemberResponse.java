package group.bonjai.bodhi.responses;

import group.bonjai.bodhi.models.DepartmentMember;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.UUID;

public class CreateDepartmentMemberResponse extends HttpResponse{
    private final CreatedDepartmentMember createdDepartmentMember;

    @Data
    @Builder
    final static class CreatedDepartmentMember{
        private final UUID id;
        private final String firstName;
        private final String middleName;
        private final String lastName;
        private final String email;
        private final String phoneNumber;
    }
    public CreateDepartmentMemberResponse(HttpStatus status, DepartmentMember createdDepartmentMember) {
        super(status);
        this.createdDepartmentMember = CreatedDepartmentMember.builder()
                .id(createdDepartmentMember.getId())
                .firstName(createdDepartmentMember.getFirstName())
                .middleName(createdDepartmentMember.getMiddleName())
                .lastName(createdDepartmentMember.getLastName())
                .email(createdDepartmentMember.getEmail())
                .phoneNumber(createdDepartmentMember.getPhoneNumber())
                .build();

    }
    public CreatedDepartmentMember getCreatedDepartmentMember() {
        return createdDepartmentMember;
    }
}
