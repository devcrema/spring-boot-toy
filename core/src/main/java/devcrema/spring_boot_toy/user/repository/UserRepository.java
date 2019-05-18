package devcrema.spring_boot_toy.user.repository;

import devcrema.spring_boot_toy.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
