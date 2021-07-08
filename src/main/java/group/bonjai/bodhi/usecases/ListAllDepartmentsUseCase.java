package group.bonjai.bodhi.usecases;

import group.bonjai.bodhi.models.Department;
import group.bonjai.bodhi.models.Teacher;
import group.bonjai.bodhi.repositories.DepartmentRepository;
import group.bonjai.bodhi.repositories.TeacherRepository;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ListAllDepartmentsUseCase implements IListAllDepartmentsUseCase {
    private final DepartmentRepository departmentRepository;
    private final TeacherRepository teacherRepository;

    public ListAllDepartmentsUseCase(DepartmentRepository departmentRepository, TeacherRepository teacherRepository) {
        this.departmentRepository = departmentRepository;
        this.teacherRepository = teacherRepository;
    }

    @Override
    public Map<Department, List<Teacher>> execute() {
        Map<Department, List<Teacher>> departmentTeachersMap = new HashMap<>();
        List<Department> departments = departmentRepository.findAll();
        departments.forEach(department -> departmentTeachersMap.put(department,
                teacherRepository.findAllByDepartmentAndRole(department, Teacher.HOD))
        );
        return departmentTeachersMap;
    }

}
