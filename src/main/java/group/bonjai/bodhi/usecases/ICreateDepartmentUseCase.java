package group.bonjai.bodhi.usecases;

import group.bonjai.bodhi.exceptions.UniqueConstraintViolation;
import group.bonjai.bodhi.models.Department;
import group.bonjai.bodhi.models.DepartmentMember;

public interface ICreateDepartmentUseCase {
    Department execute(Department department, DepartmentMember hod) throws UniqueConstraintViolation;
}
