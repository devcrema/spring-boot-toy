package devcrema.spring_boot_toy.user;

import devcrema.spring_boot_toy.CustomTestConfiguration;
import devcrema.spring_boot_toy.service.CustomPasswordEncoder;
import devcrema.spring_boot_toy.test_fixture.UserFixtureGenerator;
import devcrema.spring_boot_toy.user.repository.UserRepository;
import devcrema.spring_boot_toy.user.service.SignUpUserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = CustomTestConfiguration.class)
@ActiveProfiles(profiles = "test")
@Transactional
@Slf4j
public class SignUpUserServiceTests {

    @Autowired
    private SignUpUserService signUpUserService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CustomPasswordEncoder customPasswordEncoder;

    @Test
    @DisplayName("유저가 정상적으로 저장됨")
    public void userSavedNormally(){
        //given
        User user = UserFixtureGenerator.buildTestUserVo();
        long count = userRepository.count();
        //when
        signUpUserService.signUp(user);
        //then
        assertThat(userRepository.count()).isEqualTo(count + 1);
    }

    @Test
    @DisplayName("중복된 이메일의 유저가 있으면 오류 발생")
    public void errorOccurredIfDuplicateEmailUsersExist(){
        //given
        User user = UserFixtureGenerator.buildTestUserVo();
        userRepository.save(user);
        //when, then
        assertThrows(DuplicatedEmailException.class, ()->signUpUserService.signUp(user));
    }

    @Test
    @DisplayName("유저는 제대로 초기화가 되있어야함")
    public void usersMustBeProperlyInitialized(){
        //given
        User user = UserFixtureGenerator.buildTestUserVo();
        //when
        signUpUserService.signUp(user);
        //then
        User savedUser = userRepository.findUserByEmail(user.getEmail()).orElseThrow(RuntimeException::new);
        assertThat(customPasswordEncoder.matches(UserFixtureGenerator.PASSWORD, savedUser.getPassword())).isTrue();
    }


}
