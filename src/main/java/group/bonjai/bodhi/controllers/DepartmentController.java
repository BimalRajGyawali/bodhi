package group.bonjai.bodhi.controllers;

import group.bonjai.bodhi.models.Department;
import group.bonjai.bodhi.models.Teacher;
import group.bonjai.bodhi.requests.DepartmentCreationRequest;
import group.bonjai.bodhi.responses.DepartmentCreationResponse;
import group.bonjai.bodhi.responses.DepartmentListResponse;
import group.bonjai.bodhi.services.DepartmentService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping(value = "/")
    public DepartmentCreationResponse createDepartment(@RequestBody DepartmentCreationRequest request){
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

        Department persistedDepartment = departmentService.createNewDepartment(department, hod);

        return new DepartmentCreationResponse(HttpStatus.CREATED)
                        .departmentUUID(persistedDepartment.getId())
                        .departmentFullName(persistedDepartment.getFullName())
                        .departmentShortName(persistedDepartment.getShortName());

    }

    @GetMapping("/")
    public Page<Department> showDepartments(@RequestParam(required = false) Integer page,
                                            @RequestParam(required = false) Integer size){

        page = page==null ? 1 : page ;
        size = size == null ? 10 : size;
        System.out.println("departmentService "+departmentService);
        return departmentService.getDepartments(page, size);
    }
}
