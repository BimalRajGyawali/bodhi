package group.bonjai.bodhi.usecases;

import group.bonjai.bodhi.models.Department;
import group.bonjai.bodhi.models.DepartmentMember;
import group.bonjai.bodhi.repositories.DepartmentRepository;
import group.bonjai.bodhi.repositories.DepartmentMemberRepository;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ListAllDepartmentsUseCase implements IListAllDepartmentsUseCase {
    private final DepartmentRepository departmentRepository;
    private final DepartmentMemberRepository departmentMemberRepository;

    public ListAllDepartmentsUseCase(DepartmentRepository departmentRepository, DepartmentMemberRepository departmentMemberRepository) {
        this.departmentRepository = departmentRepository;
        this.departmentMemberRepository = departmentMemberRepository;
    }

    @Override
    public Map<Department, DepartmentMember> execute() {
        Map<Department, DepartmentMember> departmentHodMap = new HashMap<>();
        List<Department> departments = departmentRepository.findAll();
        departments.forEach(department -> departmentHodMap.put(department,
                departmentMemberRepository.findByDepartmentAndRole(department, DepartmentMember.HOD).get())
        );
        return departmentHodMap;
    }

}
