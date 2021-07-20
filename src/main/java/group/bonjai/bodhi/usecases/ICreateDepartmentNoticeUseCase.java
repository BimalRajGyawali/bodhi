package group.bonjai.bodhi.usecases;

import group.bonjai.bodhi.exceptions.ResourceNotFound;
import group.bonjai.bodhi.exceptions.UnAuthorized;
import group.bonjai.bodhi.models.DepartmentNotice;
import group.bonjai.bodhi.models.User;

import java.util.UUID;

public interface ICreateDepartmentNoticeUseCase {
    DepartmentNotice execute(String title, String content, UUID departmentId, User creator)
            throws ResourceNotFound, UnAuthorized;
}
