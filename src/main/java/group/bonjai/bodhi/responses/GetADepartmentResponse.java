package group.bonjai.bodhi.responses;

import group.bonjai.bodhi.models.Department;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.UUID;

public class GetADepartmentResponse extends HttpResponse{
    private final PersistedDepartment persistedDepartment;

    @Data
    @Builder
    final static class PersistedDepartment {
        private UUID id;
        private String fullName;
        private String shortName;
    }

    public GetADepartmentResponse(HttpStatus status, Department department) {
        super(status);
        this.persistedDepartment = PersistedDepartment.builder()
                .id(department.getId())
                .fullName(department.getFullName())
                .shortName(department.getShortName())
                .build();
    }

    public PersistedDepartment getPersistedDepartment() {
        return persistedDepartment;
    }
}
