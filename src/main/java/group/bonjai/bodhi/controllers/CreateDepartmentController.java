package group.bonjai.bodhi.controllers;

import group.bonjai.bodhi.exceptions.ConstraintViolation;
import group.bonjai.bodhi.models.Department;
import group.bonjai.bodhi.models.DepartmentMember;
import group.bonjai.bodhi.requests.CreateDepartmentRequest;
import group.bonjai.bodhi.responses.CreateDepartmentResponse;
import group.bonjai.bodhi.usecases.ICreateDepartmentUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Locale;

@RestController
@RequestMapping(path = "/departments")
public class CreateDepartmentController {
    private final ICreateDepartmentUseCase createDepartmentUseCase;

    public CreateDepartmentController(ICreateDepartmentUseCase createDepartmentUseCase) {
        this.createDepartmentUseCase = createDepartmentUseCase;
    }

    @PostMapping(value = "/")
    public CreateDepartmentResponse createDepartment(@Valid @RequestBody CreateDepartmentRequest request) throws ConstraintViolation {
        Department department = Department.builder()
                .fullName(request.getFullName())
                .shortName(request.getShortName().toUpperCase())
                .build();


        DepartmentMember hod = DepartmentMember.builder()
                .email(request.getHodEmail())
                .firstName(request.getHodFirstName())
                .middleName(request.getHodMiddleName())
                .lastName(request.getHodLastName())
                .phoneNumber(request.getHodPhoneNumber())
                .password("PASSWORD")
                .role(DepartmentMember.HOD)
                .build();

        Department persistedDepartment = createDepartmentUseCase.execute(department, hod);

        return new CreateDepartmentResponse(HttpStatus.CREATED)
                .departmentUUID(persistedDepartment.getId())
                .departmentFullName(persistedDepartment.getFullName())
                .departmentShortName(persistedDepartment.getShortName());

    }
}
