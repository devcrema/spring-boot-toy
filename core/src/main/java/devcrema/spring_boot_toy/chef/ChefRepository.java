package devcrema.spring_boot_toy.chef;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface ChefRepository extends JpaRepository<Chef, Long> {
    Optional<UserDetails> findByEmail(String email);

    Optional<Chef> findChefByEmail(String email);
}
