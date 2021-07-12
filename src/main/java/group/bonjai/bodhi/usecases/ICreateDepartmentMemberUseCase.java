package group.bonjai.bodhi.usecases;

import group.bonjai.bodhi.exceptions.ResourceNotFound;
import group.bonjai.bodhi.exceptions.UniqueConstraintViolation;
import group.bonjai.bodhi.models.DepartmentMember;

import java.util.UUID;

public interface ICreateDepartmentMemberUseCase {
    DepartmentMember execute(DepartmentMember departmentMember, UUID departmentId) throws UniqueConstraintViolation, ResourceNotFound;
}
