package group.bonjai.bodhi.responses;

import group.bonjai.bodhi.requests.DepartmentCreationRequest;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.UUID;

public class DepartmentCreationResponse extends HttpResponse{

    @Data
    final static class PersistedDepartment implements Serializable {
        private UUID id;
        private String fullName;
        private String shortName;
    }

    private PersistedDepartment persistedDepartment;

    public DepartmentCreationResponse(HttpStatus status) {
        super(status);
        this.persistedDepartment = new PersistedDepartment();
    }
    public DepartmentCreationResponse departmentUUID(UUID id){
        persistedDepartment.setId(id);
        return this;
    }
    public DepartmentCreationResponse departmentFullName(String fullName){
        persistedDepartment.setFullName(fullName);
        return this;
    }
    public DepartmentCreationResponse departmentShortName(String shortName){
        persistedDepartment.setShortName(shortName);
        return this;
    }

    public PersistedDepartment getPersistedDepartment() {
        return persistedDepartment;
    }

    public DepartmentCreationResponse setPersistedDepartment(PersistedDepartment persistedDepartment) {
        this.persistedDepartment = persistedDepartment;
        return this;
    }
}
