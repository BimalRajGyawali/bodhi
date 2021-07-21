package group.bonjai.bodhi.controllers;

import group.bonjai.bodhi.exceptions.ResourceNotFound;
import group.bonjai.bodhi.exceptions.UnAuthorized;
import group.bonjai.bodhi.jwt.Authorizer;
import group.bonjai.bodhi.models.DepartmentNotice;
import group.bonjai.bodhi.models.Roles;
import group.bonjai.bodhi.models.User;
import group.bonjai.bodhi.responses.ListAllDepartmentNoticesResponse;
import group.bonjai.bodhi.responses.ListAllDepartmentsResponse;
import group.bonjai.bodhi.usecases.ListAllDepartmentNoticesUseCase;
import group.bonjai.bodhi.usecases.ListAllDepartmentsUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.*;

@RestController
public class ListAllDepartmentNoticesController {

    private final ListAllDepartmentNoticesUseCase listAllDepartmentNoticesUseCase;

    public ListAllDepartmentNoticesController(ListAllDepartmentNoticesUseCase listAllDepartmentNoticesUseCase) {
        this.listAllDepartmentNoticesUseCase = listAllDepartmentNoticesUseCase;
    }

    @GetMapping("departments/{departmentId}/department-notices")
    public ListAllDepartmentNoticesResponse listAll(@NotNull @PathVariable UUID departmentId,
                                              Authentication authentication)
    throws ResourceNotFound, UnAuthorized {
        // authorizer
//        User user = Authorizer.authorizeIfUserHasAuthority(
//                new HashSet<String>(
//                        Arrays.asList(Roles.ADMIN, Roles.HOD, Roles.TEACHER, Roles.ASSISTANT,
//                                Roles.STUDENT)
//                ),
//                authentication
//        );
        User user = new User("","");
        List<DepartmentNotice> departmentNotices =
                listAllDepartmentNoticesUseCase.execute(departmentId, user);

        return new ListAllDepartmentNoticesResponse(HttpStatus.OK, departmentNotices);
    }
}
