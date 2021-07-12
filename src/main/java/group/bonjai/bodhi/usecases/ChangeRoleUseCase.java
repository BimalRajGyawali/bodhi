package group.bonjai.bodhi.usecases;

import group.bonjai.bodhi.exceptions.ConstraintViolation;
import group.bonjai.bodhi.exceptions.ResourceNotFound;
import group.bonjai.bodhi.models.DepartmentMember;
import group.bonjai.bodhi.repositories.DepartmentMemberRepository;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ChangeRoleUseCase implements IChangeRoleUseCase{
    private final DepartmentMemberRepository departmentMemberRepository;

    public ChangeRoleUseCase(DepartmentMemberRepository departmentMemberRepository) {
        this.departmentMemberRepository = departmentMemberRepository;
    }

    /**
     * The role of only Teacher or Assistant can be changed through this use case.
     *
     * Role of HOD cannot be changed through this use case. To change role of HOD, one must go through
     * `appoint a new HOD` use case.
     *
     * Role of any department member cannot be changed to HOD. For this also, one must go through
     * `appoint a new HOD` use case.
     *
     * The new role of Teacher or Assistant must be again Teacher or Assistant. A Teacher can be changed to Assistant
     * or an Assistant can be changed to Teacher through this use case.
     */
    @Override
    public void execute(UUID departmentMemberId, String newRole)
            throws ResourceNotFound, ConstraintViolation {

        final Set<String> permittedRoles = new HashSet<>
                (Arrays.asList(DepartmentMember.TEACHER, DepartmentMember.ASSISTANT));

        Optional<DepartmentMember> optionalDepartmentMember = departmentMemberRepository.findById(departmentMemberId);

        if(!optionalDepartmentMember.isPresent()){
            throw new ResourceNotFound(departmentMemberId);
        }
        DepartmentMember departmentMember = optionalDepartmentMember.get();

        if(!permittedRoles.contains(newRole)){
          throw new ConstraintViolation("role", "newRole must be in "+permittedRoles);
        }

        if(!permittedRoles.contains(departmentMember.getRole())){
            throw new ConstraintViolation("role", "role of department member must be in "+permittedRoles);
        }
        departmentMember.setRole(newRole);
        departmentMemberRepository.save(departmentMember);
    }
}
