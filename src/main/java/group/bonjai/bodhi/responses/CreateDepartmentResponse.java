package group.bonjai.bodhi.responses;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.UUID;

public class CreateDepartmentResponse extends HttpResponse{

    @Data
    final static class PersistedDepartment implements Serializable {
        private UUID id;
        private String fullName;
        private String shortName;
    }

    private PersistedDepartment persistedDepartment;

    public CreateDepartmentResponse(HttpStatus status) {
        super(status);
        this.persistedDepartment = new PersistedDepartment();
    }
    public CreateDepartmentResponse departmentUUID(UUID id){
        persistedDepartment.setId(id);
        return this;
    }
    public CreateDepartmentResponse departmentFullName(String fullName){
        persistedDepartment.setFullName(fullName);
        return this;
    }
    public CreateDepartmentResponse departmentShortName(String shortName){
        persistedDepartment.setShortName(shortName);
        return this;
    }

    public PersistedDepartment getPersistedDepartment() {
        return persistedDepartment;
    }

    public CreateDepartmentResponse setPersistedDepartment(PersistedDepartment persistedDepartment) {
        this.persistedDepartment = persistedDepartment;
        return this;
    }
}
