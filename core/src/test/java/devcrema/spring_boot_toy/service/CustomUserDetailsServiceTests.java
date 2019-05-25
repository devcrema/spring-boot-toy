package devcrema.spring_boot_toy.service;

import devcrema.spring_boot_toy.CustomTestConfiguration;
import devcrema.spring_boot_toy.chef.Chef;
import devcrema.spring_boot_toy.service.CustomPasswordEncoder;
import devcrema.spring_boot_toy.test_fixture.ChefFixtureGenerator;
import devcrema.spring_boot_toy.test_fixture.UserFixtureGenerator;
import devcrema.spring_boot_toy.user.User;
import devcrema.spring_boot_toy.user.repository.UserRepository;
import devcrema.spring_boot_toy.service.CustomUserDetailsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
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
public class CustomUserDetailsServiceTests {


    @Autowired
    UserRepository userRepository;

    @Autowired
    CustomPasswordEncoder customPasswordEncoder;

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @Autowired
    UserFixtureGenerator userFixtureGenerator;
    @Autowired
    ChefFixtureGenerator chefFixtureGenerator;

    @Test
    @DisplayName("유저는 이메일로 불러올 수 있음")
    public void userCanBeLoadedByEmail(){
        //given
        User generatedUser = userFixtureGenerator.generateUser();
        String email = generatedUser.getEmail();

        //when
        User user = (User) customUserDetailsService.loadUserByUsername(email);

        //then
        assertThat(user.getEmail()).isEqualTo(email);
    }

    @Test
    @DisplayName("해당 이메일을 가진 사용자가 없는 경우 오류 발생됨")
    public void errorOccurredIfNoUserHasThatEmail(){
        //given
        String unExistentEmail = "nothing";
        //when,then
        assertThrows(UsernameNotFoundException.class, ()-> customUserDetailsService.loadUserByUsername(unExistentEmail));
    }

    @Test
    @DisplayName("요리사는 이메일로 불러올 수 있음")
    public void chefCanBeLoadedByEmail(){
        //given
        Chef generatedChef = chefFixtureGenerator.generateChef();
        String email = generatedChef.getEmail();

        //when
        UserDetails chefUserDetails = customUserDetailsService.loadUserByUsername(email);

        //then
        assertThat(chefUserDetails).isInstanceOf(Chef.class);
    }


}
