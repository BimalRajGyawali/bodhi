package group.bonjai.bodhi.usecases;

import group.bonjai.bodhi.exceptions.ResourceNotFound;
import group.bonjai.bodhi.models.Department;
import group.bonjai.bodhi.repositories.DepartmentRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class GetADepartmentUseCase implements IGetADepartmentUseCase {

    private final DepartmentRepository departmentRepository;

    public GetADepartmentUseCase(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Department execute(UUID departmentId) throws ResourceNotFound {
        Optional<Department> optionalDepartment = departmentRepository.findById(departmentId);
        if(!optionalDepartment.isPresent()){
            throw new ResourceNotFound(departmentId);
        }
        return optionalDepartment.get();
    }
}
