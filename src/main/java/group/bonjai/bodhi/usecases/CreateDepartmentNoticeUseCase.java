package group.bonjai.bodhi.usecases;

import group.bonjai.bodhi.exceptions.ResourceNotFound;
import group.bonjai.bodhi.exceptions.UnAuthorized;
import group.bonjai.bodhi.models.*;
import group.bonjai.bodhi.repositories.AdminRepository;
import group.bonjai.bodhi.repositories.DepartmentMemberRepository;
import group.bonjai.bodhi.repositories.DepartmentNoticeRepository;
import group.bonjai.bodhi.repositories.DepartmentRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class CreateDepartmentNoticeUseCase implements ICreateDepartmentNoticeUseCase {

    private final DepartmentRepository departmentRepository;
    private final DepartmentNoticeRepository departmentNoticeRepository;
    private final DepartmentMemberRepository departmentMemberRepository;

    public CreateDepartmentNoticeUseCase(DepartmentRepository departmentRepository, DepartmentNoticeRepository departmentNoticeRepository, DepartmentMemberRepository departmentMemberRepository) {
        this.departmentRepository = departmentRepository;
        this.departmentNoticeRepository = departmentNoticeRepository;
        this.departmentMemberRepository = departmentMemberRepository;
    }

    @Override
    public DepartmentNotice execute(String title, String content,
                                    UUID departmentId,
                                    User creator)
            throws ResourceNotFound, UnAuthorized {

        Optional<Department> optionalDepartment = departmentRepository.findById(departmentId);

        if (!optionalDepartment.isPresent()) {
            throw new ResourceNotFound(departmentId);
        }

        Optional<DepartmentMember> optionalDepartmentMember = departmentMemberRepository
                .findByDepartmentAndEmailAndRole(optionalDepartment.get(),creator.getEmail(), creator.getRole());

        if (!optionalDepartmentMember.isPresent()) {
            throw new UnAuthorized();
        }
        DepartmentNotice departmentNotice = DepartmentNotice.builder()
                .title(title)
                .content(content)
                .department(optionalDepartment.get())
                .creator(optionalDepartmentMember.get())
                .build();

        return departmentNoticeRepository.save(departmentNotice);
    }
}
