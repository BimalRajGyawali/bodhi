package group.bonjai.bodhi.controllers;

import group.bonjai.bodhi.exceptions.ResourceNotFound;
import group.bonjai.bodhi.exceptions.ConstraintViolation;
import group.bonjai.bodhi.models.DepartmentMember;
import group.bonjai.bodhi.requests.CreateDepartmentMemberRequest;
import group.bonjai.bodhi.responses.CreateDepartmentMemberResponse;
import group.bonjai.bodhi.usecases.ICreateDepartmentMemberUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

@RestController
public class CreateDepartmentMemberController {
    private final ICreateDepartmentMemberUseCase createDepartmentMemberUseCase;

    public CreateDepartmentMemberController(ICreateDepartmentMemberUseCase createDepartmentMemberUseCase) {
        this.createDepartmentMemberUseCase = createDepartmentMemberUseCase;
    }

    @PostMapping("/departmentmembers")
    public CreateDepartmentMemberResponse create(@Valid @RequestBody CreateDepartmentMemberRequest request)
            throws ConstraintViolation, ResourceNotFound {

        DepartmentMember departmentMemberToBeCreated = DepartmentMember.builder()
                .firstName(request.getFirstName())
                .middleName(request.getMiddleName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .role(request.getRole())
                .password("DEFAULT_PASSWORD")
                .build();

        UUID departmentId = request.getDepartmentId();

        DepartmentMember createdDepartmentMember = createDepartmentMemberUseCase.execute(departmentMemberToBeCreated, departmentId);

        return new CreateDepartmentMemberResponse(HttpStatus.OK, createdDepartmentMember);
    }
}
