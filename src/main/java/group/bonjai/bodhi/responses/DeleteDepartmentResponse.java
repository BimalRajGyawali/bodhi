package group.bonjai.bodhi.responses;

import group.bonjai.bodhi.models.Department;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.UUID;

public class DeleteDepartmentResponse extends HttpResponse{
    private final DeletedDepartment deletedDepartment;

    @Data
    @Builder
    static final class DeletedDepartment{
        private final UUID id;
        private final String fullName;
        private final String shortName;
    }

    public DeleteDepartmentResponse(HttpStatus status, Department deletedDepartment) {
        super(status);
        this.deletedDepartment = DeletedDepartment.builder()
        .id(deletedDepartment.getId())
        .fullName(deletedDepartment.getFullName())
        .shortName(deletedDepartment.getShortName())
        .build();
    }

    public DeletedDepartment getDeletedDepartment(){
        return deletedDepartment;
    }
}
