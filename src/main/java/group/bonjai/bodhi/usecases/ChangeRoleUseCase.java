package group.bonjai.bodhi.usecases;

import group.bonjai.bodhi.exceptions.ResourceNotFound;
import group.bonjai.bodhi.repositories.DepartmentMemberRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ChangeRoleUseCase implements IChangeRoleUseCase{
    private final DepartmentMemberRepository departmentMemberRepository;

    public ChangeRoleUseCase(DepartmentMemberRepository departmentMemberRepository) {
        this.departmentMemberRepository = departmentMemberRepository;
    }

    @Override
    public void execute(UUID departmentMemberId, String newRole) throws ResourceNotFound{
        if(!departmentMemberRepository.existsById(departmentMemberId)){
            throw new ResourceNotFound(departmentMemberId);
        }

    }
}
