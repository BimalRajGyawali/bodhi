package group.bonjai.bodhi.usecases;

import group.bonjai.bodhi.models.Department;
import group.bonjai.bodhi.repositories.DepartmentRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ListAllDepartmentsUseCase implements IListAllDepartmentsUseCase{
    private final DepartmentRepository departmentRepository;

    public ListAllDepartmentsUseCase(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public List<Department> execute() {
        return departmentRepository.findAll();
    }
}
