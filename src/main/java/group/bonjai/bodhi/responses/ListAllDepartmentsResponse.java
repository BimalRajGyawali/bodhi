package group.bonjai.bodhi.responses;

import group.bonjai.bodhi.models.Department;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class ListAllDepartmentsResponse extends HttpResponse{
    @Data
    final static class DepartmentRecord{
        private final UUID id;
        private final String fullName;
        private final String shortName;
    }
    private List<DepartmentRecord> departmentRecords = new ArrayList<>();

    public ListAllDepartmentsResponse(HttpStatus status, List<Department> departments){
        super(status);

        this.departmentRecords =  departments.stream()
                                        .map(department -> new DepartmentRecord(department.getId(),
                                                           department.getFullName(), department.getShortName()))
                                        .collect(Collectors.toList());
    }
    public List<DepartmentRecord> getDepartmentRecords() {
        return departmentRecords;
    }

    public ListAllDepartmentsResponse setDepartmentRecords(List<DepartmentRecord> departmentRecords) {
        this.departmentRecords = departmentRecords;
        return this;
    }
}
