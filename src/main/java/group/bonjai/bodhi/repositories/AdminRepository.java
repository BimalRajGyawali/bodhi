package group.bonjai.bodhi.repositories;

import group.bonjai.bodhi.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public interface AdminRepository extends JpaRepository<Admin, UUID> {
     boolean existsByEmail(String email);
     Optional<Admin> findByEmail(String email);
}
