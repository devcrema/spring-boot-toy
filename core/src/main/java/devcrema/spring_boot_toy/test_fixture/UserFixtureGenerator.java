package devcrema.spring_boot_toy.test_fixture;

import devcrema.spring_boot_toy.config.AuthorityManager;
import devcrema.spring_boot_toy.user.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserFixtureGenerator {

    public static final String EMAIL = "test@test.com";
    public static final String NICKNAME = "nickname";
    public static final String PASSWORD = "password";

    private final UserRepository userRepository;
    private final UserPasswordEncoder userPasswordEncoder;
    private final AuthorityManager authorityManager;

    public User generateUser() {
        Optional<User> optionalUser = userRepository.findByEmail(EMAIL);
        return optionalUser.orElseGet(()
                -> userRepository.save(
                buildTestUserVo().initialize(
                        userPasswordEncoder, Collections.singletonList(
                                authorityManager.findRole(AuthorityManager.RoleType.ROLE_USER)))));
    }

    public static User buildTestUserVo() {
        return User.builder()
                .email(EMAIL)
                .nickname(NICKNAME)
                .password(PASSWORD)
                .build();
    }
}
