package group.bonjai.bodhi.responses;

import group.bonjai.bodhi.models.Department;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.UUID;

public class UpdateDepartmentResponse extends HttpResponse{

    private final UpdatedDepartment updatedDepartment;

    @Data
    static final class UpdatedDepartment{
        private final UUID id;
        private final String fullName;
        private final String shortName;
    }

    public UpdateDepartmentResponse(HttpStatus status, Department updatedDepartment) {
        super(status);
        this.updatedDepartment = new UpdatedDepartment(updatedDepartment.getId(), updatedDepartment.getFullName(),
                updatedDepartment.getShortName());
    }

    public UpdatedDepartment getUpdatedDepartment() {
        return updatedDepartment;
    }
//
//    public UpdateDepartmentResponse setUpdatedDepartment(UpdatedDepartment updatedDepartment) {
//        this.updatedDepartment = updatedDepartment;
//        return this;
//    }
}
