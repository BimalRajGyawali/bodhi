package group.bonjai.bodhi.services;

import group.bonjai.bodhi.exceptions.UniqueConstraintViolation;
import group.bonjai.bodhi.models.Department;
import group.bonjai.bodhi.models.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

public interface DepartmentService {
    Department createNewDepartment(Department department, Teacher hod) throws UniqueConstraintViolation;
    Page<Department> getDepartments(int page, int pageSize);
}
