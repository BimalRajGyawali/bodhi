package group.bonjai.bodhi.usecases;

import group.bonjai.bodhi.exceptions.ResourceNotFound;
import group.bonjai.bodhi.models.Department;

import java.util.UUID;

public interface IDeleteDepartmentUseCase {
    Department execute(UUID departmentId) throws ResourceNotFound;
}
