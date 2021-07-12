package group.bonjai.bodhi.controllers;

import group.bonjai.bodhi.exceptions.ConstraintViolation;
import group.bonjai.bodhi.exceptions.ResourceNotFound;
import group.bonjai.bodhi.models.Department;
import group.bonjai.bodhi.models.DepartmentMember;
import group.bonjai.bodhi.repositories.DepartmentMemberRepository;
import group.bonjai.bodhi.requests.ChangeRoleRequest;
import group.bonjai.bodhi.responses.ChangeRoleResponse;
import group.bonjai.bodhi.usecases.ChangeRoleUseCase;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
public class ChangeRoleController {

    private final ChangeRoleUseCase changeRoleUseCase;

    public ChangeRoleController(ChangeRoleUseCase changeRoleUseCase) {
        this.changeRoleUseCase = changeRoleUseCase;
    }

    /**
     * TODO : To come up with a logical URL for this use case.
     */
    @PostMapping("/departmentmembers/roles")
    public ChangeRoleResponse changeRole(@Valid @RequestBody ChangeRoleRequest request)
    throws ResourceNotFound, ConstraintViolation {

        UUID departmentMemberId = request.getDepartmentMemberId();
        String newRole = request.getNewRole().toUpperCase();

        changeRoleUseCase.execute(departmentMemberId, newRole);

        return new ChangeRoleResponse(HttpStatus.OK);
    }

}
