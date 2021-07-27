package group.bonjai.bodhi.controllers;

import group.bonjai.bodhi.exceptions.ResourceNotFound;
import group.bonjai.bodhi.exceptions.UnAuthorized;
import group.bonjai.bodhi.jwt.Authorizer;
import group.bonjai.bodhi.models.Department;
import group.bonjai.bodhi.models.DepartmentNotice;
import group.bonjai.bodhi.models.Roles;
import group.bonjai.bodhi.models.User;
import group.bonjai.bodhi.requests.CreateDepartmentNoticeRequest;
import group.bonjai.bodhi.responses.CreateDepartmentNoticeResponse;
import group.bonjai.bodhi.usecases.CreateDepartmentNoticeUseCase;
import group.bonjai.bodhi.usecases.ICreateDepartmentNoticeUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.HashSet;

@RestController
public class CreateDepartmentNoticeController {
    private final ICreateDepartmentNoticeUseCase createDepartmentNoticeUseCase;

    public CreateDepartmentNoticeController(CreateDepartmentNoticeUseCase createDepartmentNoticeUseCase) {
        this.createDepartmentNoticeUseCase = createDepartmentNoticeUseCase;
    }

    @PostMapping("/department-notices")
    public CreateDepartmentNoticeResponse create(@Valid @RequestBody  CreateDepartmentNoticeRequest request
    , Authentication authentication) throws UnAuthorized, ResourceNotFound {
//        User user = Authorizer.authorizeIfUserHasAuthority(
//                new HashSet<>(Arrays.asList(Roles.HOD, Roles.ASSISTANT)),
//                authentication
//
//        );
        DepartmentNotice notice = createDepartmentNoticeUseCase.execute(request.getTitle(), request.getContent(),
                request.getDepartmentId(), new User("biren@gmail.com", Roles.HOD));

        return new CreateDepartmentNoticeResponse(HttpStatus.OK, notice);
    }
}
