package devcrema.spring_boot_toy.user;

import devcrema.spring_boot_toy.CustomTestConfiguration;
import devcrema.spring_boot_toy.test_fixture.UserFixtureGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = CustomTestConfiguration.class)
@ActiveProfiles(profiles = "test")
@Transactional
public class UserServiceTests {


    @Autowired
    UserRepository userRepository;

    @Autowired
    UserPasswordEncoder userPasswordEncoder;

    @Autowired
    UserService userService;

    @Autowired
    UserFixtureGenerator userFixtureGenerator;

    @BeforeEach
    public void setUp(){
        userFixtureGenerator.generateUser();
    }

    @Test
    @DisplayName("유저는 이메일로 불러올 수 있음")
    public void userCanBeLoadedByEmail(){
        //given
        String email = UserFixtureGenerator.EMAIL;

        //when
        User user = (User) userService.loadUserByUsername(email);

        //then
        assertThat(user.getEmail()).isEqualTo(email);
    }

    @Test
    @DisplayName("해당 이메일을 가진 사용자가 없는 경우 오류 발생됨")
    public void errorOccurredIfNoUserHasThatEmail(){
        //given
        String unExistentEmail = "nothing";
        //when,then
        assertThrows(UsernameNotFoundException.class, ()-> userService.loadUserByUsername(unExistentEmail));
    }

}
