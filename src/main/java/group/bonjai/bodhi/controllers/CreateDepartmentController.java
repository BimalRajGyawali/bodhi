package group.bonjai.bodhi.controllers;

import group.bonjai.bodhi.exceptions.UniqueConstraintViolation;
import group.bonjai.bodhi.models.Department;
import group.bonjai.bodhi.models.Teacher;
import group.bonjai.bodhi.requests.CreateDepartmentRequest;
import group.bonjai.bodhi.responses.CreateDepartmentResponse;
import group.bonjai.bodhi.usecases.ICreateDepartmentUseCase;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/departments")
public class CreateDepartmentController {
    private final ICreateDepartmentUseCase createDepartmentUseCase;

    public CreateDepartmentController(ICreateDepartmentUseCase createDepartmentUseCase) {
        this.createDepartmentUseCase = createDepartmentUseCase;
    }

    @PostMapping(value = "/")
    public CreateDepartmentResponse createDepartment(@Valid @RequestBody CreateDepartmentRequest request) throws UniqueConstraintViolation {
        Department department = Department.builder()
                                .fullName(request.getFullName())
                                .shortName(request.getShortName())
                                .build();


        Teacher hod = Teacher.builder()
                                 .email(request.getHodEmail())
                                 .phoneNumber(request.getHodPhoneNumber())
                                 .password("PASSWORD")
                                 .role(Teacher.HOD)
                                 .build();

        Department persistedDepartment = createDepartmentUseCase.execute(department, hod);

        return new CreateDepartmentResponse(HttpStatus.CREATED)
                        .departmentUUID(persistedDepartment.getId())
                        .departmentFullName(persistedDepartment.getFullName())
                        .departmentShortName(persistedDepartment.getShortName());

    }
}
