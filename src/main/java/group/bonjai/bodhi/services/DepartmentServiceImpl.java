package group.bonjai.bodhi.services;

import group.bonjai.bodhi.exceptions.UniqueConstraintViolation;
import group.bonjai.bodhi.models.Department;
import group.bonjai.bodhi.models.Teacher;
import group.bonjai.bodhi.repositories.DepartmentRepository;
import group.bonjai.bodhi.repositories.TeacherRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

@Component
public class DepartmentServiceImpl implements  DepartmentService{
    private final DepartmentRepository departmentRepository;
    private final TeacherRepository teacherRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository, TeacherRepository teacherRepository) {
        this.departmentRepository = departmentRepository;
        this.teacherRepository = teacherRepository;
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
    public Department createNewDepartment(Department department, Teacher hod)
            throws UniqueConstraintViolation {

        if(departmentRepository.existsByFullName(department.getFullName())){
            throw new UniqueConstraintViolation("fullName",
                    "Department "+department.getFullName()+" already exists");
        }

        if(departmentRepository.existsByShortName(department.getShortName())){
            throw new UniqueConstraintViolation("shortName",
                    "ShortName "+department.getShortName()+" already exists");
        }
        if(teacherRepository.existsByEmail(hod.getEmail())){
            throw new UniqueConstraintViolation("email",
                    "Hod with email "+hod.getEmail()+" already exists");
        }
        if(teacherRepository.existsByPhoneNumber(hod.getPhoneNumber())){
            throw new UniqueConstraintViolation("phoneNumber",
                    "Hod with phone "+hod.getPhoneNumber()+" already exists");
        }

        Department persistedDepartment = departmentRepository.save(department);

        hod.setDepartment(persistedDepartment);
        teacherRepository.save(hod);

        return persistedDepartment;
    }

    @Override
    public Page<Department> getDepartments(int page, int pageSize) {
         return departmentRepository.findAll(PageRequest.of(page, pageSize));
    }
}
