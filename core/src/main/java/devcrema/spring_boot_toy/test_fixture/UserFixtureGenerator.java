package devcrema.spring_boot_toy.test_fixture;

import devcrema.spring_boot_toy.user.User;
import devcrema.spring_boot_toy.service.CustomPasswordEncoder;
import devcrema.spring_boot_toy.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserFixtureGenerator {

    public static final String EMAIL = "user@test.com";
    public static final String NICKNAME = "userName";
    public static final String PASSWORD = "userPassword";

    private final UserRepository userRepository;
    private final CustomPasswordEncoder customPasswordEncoder;

    public User generateUser() {
        Optional<User> optionalUser = userRepository.findUserByEmail(EMAIL);
        return optionalUser.orElseGet(()
                -> userRepository.save(buildTestUserVo().initialize(customPasswordEncoder)));
    }

    public static User buildTestUserVo() {
        return User.builder()
                .email(EMAIL)
                .nickname(NICKNAME)
                .password(PASSWORD)
                .build();
    }
}
