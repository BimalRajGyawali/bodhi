package group.bonjai.bodhi.usecases;

import group.bonjai.bodhi.exceptions.ResourceNotFound;
import group.bonjai.bodhi.models.Department;
import group.bonjai.bodhi.models.DepartmentNotice;
import group.bonjai.bodhi.models.User;
import group.bonjai.bodhi.repositories.DepartmentNoticeRepository;
import group.bonjai.bodhi.repositories.DepartmentRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class ListAllDepartmentNoticesUseCase implements IListAllDepartmentNoticesUseCase{

    private final DepartmentNoticeRepository departmentNoticeRepository;
    private final DepartmentRepository departmentRepository;

    public ListAllDepartmentNoticesUseCase(DepartmentNoticeRepository departmentNoticeRepository, DepartmentRepository departmentRepository) {
        this.departmentNoticeRepository = departmentNoticeRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public List<DepartmentNotice> execute(UUID departmentId, User user ) throws ResourceNotFound {
        Optional<Department> optionalDepartment = departmentRepository.findById(departmentId);
        if(!optionalDepartment.isPresent()){
            throw new ResourceNotFound(departmentId);
        }
        Department department = optionalDepartment.get();

        //TODO:  check for student if he/she belongs to that department

        return departmentNoticeRepository.findByDepartment(department);
    }
}
