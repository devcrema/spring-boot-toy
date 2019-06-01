package devcrema.spring_boot_toy.service;

import devcrema.spring_boot_toy.CustomTestConfiguration;
import devcrema.spring_boot_toy.chef.Chef;
import devcrema.spring_boot_toy.test_fixture.ChefFixtureGenerator;
import devcrema.spring_boot_toy.test_fixture.UserFixtureGenerator;
import devcrema.spring_boot_toy.user.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = CustomTestConfiguration.class)
@ActiveProfiles(profiles = "test")
@Transactional
public class CheckDuplicatedEmailServiceTests {

    @Autowired
    CheckDuplicatedEmailService checkDuplicatedEmailService;
    @Autowired
    UserFixtureGenerator userFixtureGenerator;
    @Autowired
    ChefFixtureGenerator chefFixtureGenerator;

    @Test
    @DisplayName("모든 유저 타입중에 중복된 이메일이 존재하면 true")
    public void trueIfDuplicateEmailsExistAmongAllUserTypes(){
        //given
        userFixtureGenerator.generateUser();
        Chef generatedChef = chefFixtureGenerator.generateChef();
        String email = generatedChef.getEmail();
        //when
        boolean duplicatedResult = checkDuplicatedEmailService.checkDuplicatedEmail(email);
        //then
        assertThat(duplicatedResult).isTrue();
    }

    @Test
    @DisplayName("모든 유저 타입에서 중복된 이메일이 없으면 false")
    public void falseIfDuplicateEmailsDoesNotExistAmongAllUserTypes(){
        //given
        userFixtureGenerator.generateUser();
        chefFixtureGenerator.generateChef();
        String email = "none@test.com";
        //when
        boolean duplicatedResult = checkDuplicatedEmailService.checkDuplicatedEmail(email);
        //then
        assertThat(duplicatedResult).isFalse();
    }


}
