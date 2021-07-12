package group.bonjai.bodhi.controllers;

import group.bonjai.bodhi.exceptions.ResourceNotFound;
import group.bonjai.bodhi.models.Department;
import group.bonjai.bodhi.responses.DeleteDepartmentResponse;
import group.bonjai.bodhi.usecases.DeleteDepartmentUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@RestController
public class DeleteDepartmentController {
    private final DeleteDepartmentUseCase deleteDepartmentUseCase;

    public DeleteDepartmentController(DeleteDepartmentUseCase deleteDepartmentUseCase) {
        this.deleteDepartmentUseCase = deleteDepartmentUseCase;
    }

    @DeleteMapping("/departments/{id}")
    public DeleteDepartmentResponse delete(@NotNull @PathVariable UUID id) throws ResourceNotFound {
        Department deletedDepartment = deleteDepartmentUseCase.execute(id);
        return new DeleteDepartmentResponse(HttpStatus.OK, deletedDepartment);
    }
}
