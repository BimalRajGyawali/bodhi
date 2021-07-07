package group.bonjai.bodhi.services;

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
    * Create a new Department in db
    * Associates Teacher with Department
    * Create a new Teacher in db
    *
    * @returns persisted Department object with id
    * */
    @Override
    public Department createNewDepartment(Department department, Teacher hod) {
        Department persistedDepartment = departmentRepository.save(department);
        hod.setDepartment(persistedDepartment);
        teacherRepository.save(hod);
        System.out.println(persistedDepartment);
        return persistedDepartment;
    }

    @Override
    public Page<Department> getDepartments(int page, int pageSize) {
         return departmentRepository.findAll(PageRequest.of(page, pageSize));
    }
}
