package group.bonjai.bodhi.usecases;

import group.bonjai.bodhi.exceptions.ConstraintViolation;
import group.bonjai.bodhi.exceptions.ResourceNotFound;
import group.bonjai.bodhi.models.DepartmentMember;

import java.util.UUID;

public interface ITransferADepartmentMemberUseCase {
    DepartmentMember execute(UUID departmentMemberId, UUID departmentId, String newRole) throws ResourceNotFound, ConstraintViolation;
}
