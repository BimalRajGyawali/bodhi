package group.bonjai.bodhi.repositories;

import group.bonjai.bodhi.models.Department;
import group.bonjai.bodhi.models.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public interface TeacherRepository extends JpaRepository<Teacher, UUID>{
    boolean existsByEmail(String email);
    boolean existsByPhoneNumber(String phoneNumber);
    List<Teacher> findAllByDepartmentAndRole(Department department, String role);
}
