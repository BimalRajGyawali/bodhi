package group.bonjai.bodhi.usecases;

import group.bonjai.bodhi.exceptions.ConstraintViolation;
import group.bonjai.bodhi.exceptions.ResourceNotFound;
import group.bonjai.bodhi.models.DepartmentMember;
import group.bonjai.bodhi.repositories.DepartmentMemberRepository;
import group.bonjai.bodhi.repositories.DepartmentRepository;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Component
public class AppointANewHODUseCase implements IAppointANewHODUseCase{
    private final DepartmentRepository departmentRepository;
    private final DepartmentMemberRepository departmentMemberRepository;

    public AppointANewHODUseCase(DepartmentRepository departmentRepository, DepartmentMemberRepository departmentMemberRepository) {
        this.departmentRepository = departmentRepository;
        this.departmentMemberRepository = departmentMemberRepository;
    }

    /*
    * New HOD must be a department member of that department
    * newHODId is the id of department member who is going to be HOD of his/her department
    * */

    @Override
    @Transactional
    public DepartmentMember execute(UUID newHODId, String newRoleOfOldHOD) throws ResourceNotFound,
            ConstraintViolation{
        Optional<DepartmentMember> optionalNewHOD = departmentMemberRepository.findById(newHODId);

        if(!optionalNewHOD.isPresent()){
            throw new ResourceNotFound(newHODId);
        }
        if(newRoleOfOldHOD.equalsIgnoreCase(DepartmentMember.HOD)){
            throw new ConstraintViolation("newRoleOfOldHOD",
                    "Old HOD cannot be made HOD");
        }

        DepartmentMember newHOD = optionalNewHOD.get();

        if(newHOD.getRole().equalsIgnoreCase(DepartmentMember.HOD)){
            throw new ConstraintViolation("newHODId", newHODId+" is already a HOD");
        }

        DepartmentMember oldHOD = departmentMemberRepository.
                findByDepartmentAndRole(newHOD.getDepartment(), DepartmentMember.HOD);


        oldHOD.setRole(newRoleOfOldHOD);
        newHOD.setRole(DepartmentMember.HOD);

        return newHOD;

    }
}
