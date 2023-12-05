package phongvan.javaspringbootbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import phongvan.javaspringbootbackend.entity.Group;
import phongvan.javaspringbootbackend.entity.Role;

public interface RoleRepository extends JpaRepository<Role,Integer> {

    Role findByName(String roleName);
}
