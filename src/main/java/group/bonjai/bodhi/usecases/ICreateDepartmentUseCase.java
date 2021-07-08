package group.bonjai.bodhi.usecases;

import group.bonjai.bodhi.exceptions.UniqueConstraintViolation;
import group.bonjai.bodhi.models.Department;
import group.bonjai.bodhi.models.Teacher;
import org.springframework.data.domain.Page;

public interface ICreateDepartmentUseCase {
    Department execute(Department department, Teacher hod) throws UniqueConstraintViolation;
}
