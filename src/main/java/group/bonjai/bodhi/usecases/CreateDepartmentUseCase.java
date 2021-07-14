package group.bonjai.bodhi.usecases;

import group.bonjai.bodhi.exceptions.ConstraintViolation;
import group.bonjai.bodhi.models.Department;
import group.bonjai.bodhi.models.DepartmentMember;
import group.bonjai.bodhi.repositories.DepartmentRepository;
import group.bonjai.bodhi.repositories.DepartmentMemberRepository;
import org.springframework.stereotype.Component;

@Component
public class CreateDepartmentUseCase implements ICreateDepartmentUseCase {
    private final DepartmentRepository departmentRepository;
    private final DepartmentMemberRepository departmentMemberRepository;

    public CreateDepartmentUseCase(DepartmentRepository departmentRepository, DepartmentMemberRepository departmentMemberRepository) {
        this.departmentRepository = departmentRepository;
        this.departmentMemberRepository = departmentMemberRepository;
    }

    /*UseCase : Create a New Department
    *
    * @param Department object without id
    * @param Teacher object without id
    *
    * Checks if department name already exists
    * Checks if department shortname already exists
    *
    * Creates a new Department in db
    * Associates Teacher with Department
    * Creates a new Teacher in db
    *
    * @returns persisted Department object with id
    * */
    @Override
    public Department execute(Department department, DepartmentMember hod)
            throws ConstraintViolation {

        if(departmentRepository.existsByFullName(department.getFullName())){
            throw new ConstraintViolation("fullName",
                    "Department "+department.getFullName()+" already exists");
        }

        if(departmentRepository.existsByShortName(department.getShortName())){
            throw new ConstraintViolation("shortName",
                    "ShortName "+department.getShortName()+" already exists");
        }
        if(departmentMemberRepository.existsByEmail(hod.getEmail())){
            throw new ConstraintViolation("hodEmail",
                    "Hod with email "+hod.getEmail()+" already exists");
        }
        if(departmentMemberRepository.existsByPhoneNumber(hod.getPhoneNumber())){
            throw new ConstraintViolation("hodPhoneNumber",
                    "Hod with phone "+hod.getPhoneNumber()+" already exists");
        }

        Department persistedDepartment = departmentRepository.save(department);

        hod.setDepartment(persistedDepartment);
        departmentMemberRepository.save(hod);

        return persistedDepartment;
    }

}
