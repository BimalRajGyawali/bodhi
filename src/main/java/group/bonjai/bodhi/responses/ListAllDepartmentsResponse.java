package group.bonjai.bodhi.responses;

import group.bonjai.bodhi.models.Department;
import group.bonjai.bodhi.models.Teacher;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class ListAllDepartmentsResponse extends HttpResponse {
    @Data
    final static class DepartmentRecord {
        private final UUID id;
        private final String fullName;
        private final String shortName;
        private final List<HodRecord> hodRecords;
    }

    @Data
    final static class HodRecord {
        private final UUID id;
        private final String firstName;
        private final String middleName;
        private final String lastName;
        private final String email;
        private final String phoneNumber;
    }


    private List<DepartmentRecord> departmentRecords = new ArrayList<>();

    public ListAllDepartmentsResponse(HttpStatus status, Map<Department, List<Teacher>> departmentTeachersMap) {
        super(status);

        this.departmentRecords = departmentTeachersMap.entrySet().stream()
                .map(entry -> {

                    List<HodRecord> hodRecords = entry.getValue().stream()
                            .map(teacher -> new HodRecord(teacher.getId(), teacher.getFirstName(), teacher.getMiddleName(),
                                    teacher.getLastName(), teacher.getEmail(), teacher.getPhoneNumber()))
                            .collect(Collectors.toList());


                    return new DepartmentRecord(entry.getKey().getId(),
                            entry.getKey().getFullName(),
                            entry.getKey().getShortName(),
                            hodRecords);
                })
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
