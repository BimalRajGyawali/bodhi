package group.bonjai.bodhi.repositories;

import group.bonjai.bodhi.models.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public interface DepartmentRepository extends JpaRepository<Department, UUID> {
     Department findByFullName(String fullName);
     boolean existsByFullName(String fullName);
     boolean existsByShortName(String shortName);
     Department findByShortName(String shortname);
}
