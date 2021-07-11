package group.bonjai.bodhi.controllers;

import group.bonjai.bodhi.exceptions.ResourceNotFound;
import group.bonjai.bodhi.models.Department;
import group.bonjai.bodhi.requests.UpdateDepartmentRequest;
import group.bonjai.bodhi.responses.UpdateDepartmentResponse;
import group.bonjai.bodhi.usecases.UpdateDepartmentUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UpdateDepartmentController {
    private final UpdateDepartmentUseCase updateDepartmentUseCase;

    public UpdateDepartmentController(UpdateDepartmentUseCase updateDepartmentUseCase) {
        this.updateDepartmentUseCase = updateDepartmentUseCase;
    }

    @PutMapping("/departments")
    public UpdateDepartmentResponse update(@Valid @RequestBody UpdateDepartmentRequest request) throws ResourceNotFound {
        Department department = Department.builder()
                .id(request.getId())
                .fullName(request.getNewFullName())
                .shortName(request.getNewShortName())
                .build();

         Department updatedDepartment = updateDepartmentUseCase.execute(department);
        return new UpdateDepartmentResponse(HttpStatus.OK, updatedDepartment) ;
    }
}
