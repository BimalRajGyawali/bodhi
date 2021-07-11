package group.bonjai.bodhi.usecases;

import group.bonjai.bodhi.exceptions.ResourceNotFound;
import group.bonjai.bodhi.models.Department;

public interface IUpdateDepartmentUseCase {
    Department execute(Department department) throws ResourceNotFound;
}
