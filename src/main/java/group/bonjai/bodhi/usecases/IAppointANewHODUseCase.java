package group.bonjai.bodhi.usecases;

import group.bonjai.bodhi.exceptions.ConstraintViolation;
import group.bonjai.bodhi.exceptions.ResourceNotFound;
import group.bonjai.bodhi.models.DepartmentMember;

import java.util.UUID;

public interface IAppointANewHODUseCase {
    DepartmentMember execute(UUID newHODId, String newRoleOfOldHOD) throws ResourceNotFound, ConstraintViolation;
}
