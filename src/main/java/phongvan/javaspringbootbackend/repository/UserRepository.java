package phongvan.javaspringbootbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import phongvan.javaspringbootbackend.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {

    Optional<User> findByEmail(String Email);

    Optional<User> findByPhone(String Phone);


}
