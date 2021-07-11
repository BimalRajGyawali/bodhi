package group.bonjai.bodhi.usecases;

import group.bonjai.bodhi.exceptions.ResourceNotFound;
import group.bonjai.bodhi.models.Department;
import group.bonjai.bodhi.repositories.DepartmentRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UpdateDepartmentUseCase implements IUpdateDepartmentUseCase{

    private final DepartmentRepository departmentRepository;

    public UpdateDepartmentUseCase(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Department execute(Department department) throws ResourceNotFound{
        Optional<Department> optionalDepartment = departmentRepository.findById(department.getId());

        if(!optionalDepartment.isPresent()){
            throw new ResourceNotFound(department.getId());
        }

        String newFullName = department.getFullName();
        String newShortName = department.getShortName();

        Department persistedDepartment = optionalDepartment.get();

        if(newFullName != null && !newFullName.trim().isEmpty()){
            persistedDepartment.setFullName(newFullName);
        }
        if(newShortName != null && !newShortName.trim().isEmpty()){
            persistedDepartment.setShortName(newShortName);
        }
        return departmentRepository.save(persistedDepartment);
    }
}
