package group.bonjai.bodhi.repositories;

import group.bonjai.bodhi.models.Department;
import group.bonjai.bodhi.models.DepartmentNotice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface DepartmentNoticeRepository extends JpaRepository<DepartmentNotice, UUID> {
    List<DepartmentNotice> findByDepartment(Department department);
}
