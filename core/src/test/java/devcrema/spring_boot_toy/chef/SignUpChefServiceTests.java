package devcrema.spring_boot_toy.chef;

import devcrema.spring_boot_toy.CustomTestConfiguration;
import devcrema.spring_boot_toy.service.CustomPasswordEncoder;
import devcrema.spring_boot_toy.test_fixture.ChefFixtureGenerator;
import devcrema.spring_boot_toy.test_fixture.UserFixtureGenerator;
import devcrema.spring_boot_toy.user.DuplicatedEmailException;
import devcrema.spring_boot_toy.user.User;
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
public class SignUpChefServiceTests {

    @Autowired
    private SignUpChefService signUpChefService;
    @Autowired
    private ChefRepository chefRepository;
    @Autowired
    private CustomPasswordEncoder customPasswordEncoder;

    @Test
    @DisplayName("요리사가 정상적으로 저장됨")
    public void chefSavedNormally(){
        //given
        Chef chef = ChefFixtureGenerator.buildTestChefVo();
        long count = chefRepository.count();
        //when
        signUpChefService.signUp(chef);
        //then
        assertThat(chefRepository.count()).isEqualTo(count + 1);
    }

    @Test
    @DisplayName("중복된 이메일의 요리사가 있으면 오류 발생")
    public void errorOccurredIfDuplicateEmailChefExist(){
        //given
        Chef chef = ChefFixtureGenerator.buildTestChefVo();
        chefRepository.save(chef);
        //when, then
        assertThrows(DuplicatedEmailException.class, ()->signUpChefService.signUp(chef));
    }

    @Test
    @DisplayName("유저는 제대로 초기화가 되있어야함")
    public void chefMustBeProperlyInitialized(){
        //given
        Chef chef = ChefFixtureGenerator.buildTestChefVo();
        //when
        signUpChefService.signUp(chef);
        //then
        Chef savedChef = chefRepository.findChefByEmail(chef.getEmail()).orElseThrow(RuntimeException::new);
        assertThat(customPasswordEncoder.matches(ChefFixtureGenerator.PASSWORD, savedChef.getPassword())).isTrue();
    }


}
