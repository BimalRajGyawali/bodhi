package group.bonjai.bodhi.controllers;

import group.bonjai.bodhi.models.Department;
import group.bonjai.bodhi.responses.ListAllDepartmentsResponse;
import group.bonjai.bodhi.usecases.ListAllDepartmentsUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ListAllDepartmentsController {
    private final ListAllDepartmentsUseCase listAllDepartmentsUseCase;

    public ListAllDepartmentsController(ListAllDepartmentsUseCase listAllDepartmentsUseCase) {
        this.listAllDepartmentsUseCase = listAllDepartmentsUseCase;
    }

    @GetMapping("/departments")
    public ListAllDepartmentsResponse listAllDepartment(){
        List<Department> departments = listAllDepartmentsUseCase.execute();
        return new ListAllDepartmentsResponse(HttpStatus.OK, departments);
    }
}
