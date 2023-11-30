package phongvan.javaspringbootbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import phongvan.javaspringbootbackend.entity.Group;
import phongvan.javaspringbootbackend.entity.Role;

import java.util.List;
import java.util.Optional;

public interface GroupRepository extends JpaRepository<Group,Integer> {
    Optional<Group> findByName(String name);
}
