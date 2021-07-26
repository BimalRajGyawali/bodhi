package group.bonjai.bodhi.controllers;

import group.bonjai.bodhi.exceptions.UnAuthorized;
import group.bonjai.bodhi.jwt.Authorizer;
import group.bonjai.bodhi.models.Department;
import group.bonjai.bodhi.models.DepartmentMember;
import group.bonjai.bodhi.models.Roles;
import group.bonjai.bodhi.models.User;
import group.bonjai.bodhi.responses.ListAllDepartmentsResponse;
import group.bonjai.bodhi.usecases.ListAllDepartmentsUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
public class ListAllDepartmentsController {
    private final ListAllDepartmentsUseCase listAllDepartmentsUseCase;

    public ListAllDepartmentsController(ListAllDepartmentsUseCase listAllDepartmentsUseCase) {
        this.listAllDepartmentsUseCase = listAllDepartmentsUseCase;
    }

    @GetMapping("/departments")
    public ListAllDepartmentsResponse listAllDepartments(Authentication authentication)
    throws UnAuthorized {

//        User user = Authorizer.authorizeIfUserHasAuthority(
//                Collections.singleton(Roles.ADMIN),
//                authentication
//        );
        Map<Department, DepartmentMember> departmentHodMap = listAllDepartmentsUseCase.execute();

        return new ListAllDepartmentsResponse(HttpStatus.OK, departmentHodMap);

    }
}
