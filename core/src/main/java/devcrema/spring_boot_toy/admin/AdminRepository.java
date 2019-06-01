package devcrema.spring_boot_toy.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Optional<UserDetails> findByEmail(String email);
}
