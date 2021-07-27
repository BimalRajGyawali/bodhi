package group.bonjai.bodhi.usecases;

import group.bonjai.bodhi.exceptions.ResourceNotFound;
import group.bonjai.bodhi.models.DepartmentNotice;
import group.bonjai.bodhi.models.User;

import java.util.List;
import java.util.UUID;

public interface IListAllDepartmentNoticesUseCase {
    List<DepartmentNotice> execute(UUID departmentId, User user) throws ResourceNotFound;
}
