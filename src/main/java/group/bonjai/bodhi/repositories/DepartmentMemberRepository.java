package group.bonjai.bodhi.repositories;

import group.bonjai.bodhi.models.Department;
import group.bonjai.bodhi.models.DepartmentMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public interface DepartmentMemberRepository extends JpaRepository<DepartmentMember, UUID>{
    boolean existsByEmail(String email);
    boolean existsByPhoneNumber(String phoneNumber);
    DepartmentMember findByDepartmentAndRole(Department department, String role);
    Optional<DepartmentMember> findByEmail(String email);
}
