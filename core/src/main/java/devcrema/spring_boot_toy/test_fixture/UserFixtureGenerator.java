package devcrema.spring_boot_toy.test_fixture;

import devcrema.spring_boot_toy.config.SecurityManager;
import devcrema.spring_boot_toy.user.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserFixtureGenerator {

    private final UserRepository userRepository;
    private final UserPasswordEncoder userPasswordEncoder;
    private final RoleRepository roleRepository;

    public static final User TEST_USER_VO = User.builder()
            .email("test@test.com")
            .nickname("nickname")
            .password("password")
            .build();

    public User generateUser () {
        Optional<User> optionalUser = userRepository.findByEmail(TEST_USER_VO.getEmail());
        Role role = roleRepository.findByName(SecurityManager.ROLE_USER).orElseThrow(()-> new RoleNotFoundException("유저 role이 설정되지 않음"));
        return optionalUser.orElseGet(() -> userRepository.save(TEST_USER_VO.initialize(userPasswordEncoder, Collections.singleton(role))));
    }
}
