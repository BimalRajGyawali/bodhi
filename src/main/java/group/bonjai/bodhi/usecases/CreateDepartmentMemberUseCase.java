package group.bonjai.bodhi.usecases;

import group.bonjai.bodhi.exceptions.ResourceNotFound;
import group.bonjai.bodhi.exceptions.ConstraintViolation;
import group.bonjai.bodhi.models.Department;
import group.bonjai.bodhi.models.DepartmentMember;
import group.bonjai.bodhi.repositories.DepartmentMemberRepository;
import group.bonjai.bodhi.repositories.DepartmentRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class CreateDepartmentMemberUseCase implements ICreateDepartmentMemberUseCase {
    private final DepartmentMemberRepository departmentMemberRepository;
    private final DepartmentRepository departmentRepository;

    public CreateDepartmentMemberUseCase(DepartmentMemberRepository departmentMemberRepository, DepartmentRepository departmentRepository) {
        this.departmentMemberRepository = departmentMemberRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public DepartmentMember execute(DepartmentMember departmentMember, UUID departmentId)
            throws ConstraintViolation, ResourceNotFound {

        Optional<Department> optionalDepartment = departmentRepository.findById(departmentId);

        if(!optionalDepartment.isPresent()){
            throw new ResourceNotFound(departmentId);
        }

        if(departmentMember.getRole().equalsIgnoreCase(DepartmentMember.HOD)){
           throw new ConstraintViolation("role", "Not allowed to create HOD");
       }

        if(departmentMemberRepository.existsByEmail(departmentMember.getEmail())){
            throw new ConstraintViolation("email",
                    "Department Member with email "+departmentMember.getEmail()+" already exists");
        }
        if(departmentMemberRepository.existsByPhoneNumber(departmentMember.getPhoneNumber())){
            throw new ConstraintViolation("phoneNumber", "" +
                    "Department Member with phone "+departmentMember.getPhoneNumber()+" already exists");
        }
        departmentMember.setDepartment(optionalDepartment.get());
        return departmentMemberRepository.save(departmentMember);
    }
}
