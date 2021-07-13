package group.bonjai.bodhi.usecases;

import group.bonjai.bodhi.exceptions.ConstraintViolation;
import group.bonjai.bodhi.exceptions.ResourceNotFound;
import group.bonjai.bodhi.models.Department;
import group.bonjai.bodhi.models.DepartmentMember;
import group.bonjai.bodhi.repositories.DepartmentMemberRepository;
import group.bonjai.bodhi.repositories.DepartmentRepository;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class TransferADepartmentUseCase implements ITransferADepartmentMemberUseCase{
   private final DepartmentRepository departmentRepository;
   private final DepartmentMemberRepository departmentMemberRepository;

    public TransferADepartmentUseCase(DepartmentRepository departmentRepository, DepartmentMemberRepository departmentMemberRepository) {
        this.departmentRepository = departmentRepository;
        this.departmentMemberRepository = departmentMemberRepository;
    }

    @Override
    public DepartmentMember execute(UUID departmentMemberId, UUID departmentId, String newRole)
    throws ResourceNotFound, ConstraintViolation{
        final Set<String> permittedRoles = new HashSet<>
                (Arrays.asList(DepartmentMember.TEACHER, DepartmentMember.ASSISTANT));


        Optional<DepartmentMember> optionalDepartmentMember = departmentMemberRepository.findById(departmentMemberId);
        Optional<Department> optionalDepartment = departmentRepository.findById(departmentId);

        if(!optionalDepartmentMember.isPresent()){
            throw new ResourceNotFound(departmentMemberId);
        }
        if(!optionalDepartment.isPresent()){
            throw new ResourceNotFound(departmentId);
        }

        DepartmentMember departmentMember = optionalDepartmentMember.get();
        Department department = optionalDepartment.get();

        /* Check whether department member is transferable [HOD is not transferable]*/
        if(!permittedRoles.contains(departmentMember.getRole())){
            throw new ConstraintViolation("role", departmentMember.getRole()+" cannot be transferred");
        }
        /*Department Member is transferable . Check if the newRole is empty.
        * If newRole is empty, department member is transferred to same role. */

        if(newRole == null || newRole.trim().isEmpty()){
            newRole = departmentMember.getRole();
        }
        if(!permittedRoles.contains(newRole.toUpperCase())){
            throw new ConstraintViolation("role", "Department Member cannot be transferred as "+newRole);
        }
        departmentMember.setDepartment(department);
        departmentMember.setRole(newRole);

        return departmentMemberRepository.save(departmentMember);
    }
}
