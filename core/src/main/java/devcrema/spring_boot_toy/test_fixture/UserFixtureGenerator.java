package devcrema.spring_boot_toy.test_fixture;

import devcrema.spring_boot_toy.user.User;
import devcrema.spring_boot_toy.user.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

public class UserFixtureGenerator {

    public static final User TEST_USER_VO = User.builder()
            .email("test@test.com")
            .nickname("nickname")
            .password("password")
            .build();

    public static User generateUser (UserRepository userRepository, PasswordEncoder userPasswordEncoder) {
        Optional<User> optionalUser = userRepository.findByEmail(TEST_USER_VO.getEmail());
        return optionalUser.orElseGet(() -> userRepository.save(TEST_USER_VO.initialize(userPasswordEncoder)));
    }
}
