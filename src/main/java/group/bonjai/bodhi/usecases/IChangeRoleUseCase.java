package group.bonjai.bodhi.usecases;

import group.bonjai.bodhi.exceptions.ConstraintViolation;
import group.bonjai.bodhi.exceptions.ResourceNotFound;

import java.util.UUID;

public interface IChangeRoleUseCase {
    void execute(UUID departmentMemberId, String newRole) throws ResourceNotFound, ConstraintViolation;
}
