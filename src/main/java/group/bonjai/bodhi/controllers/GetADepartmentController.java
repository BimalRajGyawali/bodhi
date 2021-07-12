package group.bonjai.bodhi.controllers;

import group.bonjai.bodhi.exceptions.ResourceNotFound;
import group.bonjai.bodhi.models.Department;
import group.bonjai.bodhi.responses.GetADepartmentResponse;
import group.bonjai.bodhi.usecases.GetADepartmentUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@RestController
public class GetADepartmentController {
    private final GetADepartmentUseCase getADepartmentUseCase;

    public GetADepartmentController(GetADepartmentUseCase getADepartmentUseCase) {
        this.getADepartmentUseCase = getADepartmentUseCase;
    }

    @GetMapping("/departments/{id}")
    public GetADepartmentResponse get(@NotNull @PathVariable UUID id) throws ResourceNotFound {
        Department department = getADepartmentUseCase.execute(id);
        return new GetADepartmentResponse(HttpStatus.OK, department);
    }
}
