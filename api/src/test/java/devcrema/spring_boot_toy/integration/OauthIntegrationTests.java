package devcrema.spring_boot_toy.integration;

import devcrema.spring_boot_toy.AccessTokenUtil;
import devcrema.spring_boot_toy.CustomTestConfiguration;
import devcrema.spring_boot_toy.chef.Chef;
import devcrema.spring_boot_toy.test_fixture.ChefFixtureGenerator;
import devcrema.spring_boot_toy.test_fixture.UserFixtureGenerator;
import devcrema.spring_boot_toy.user.User;
import devcrema.spring_boot_toy.service.CustomPasswordEncoder;
import devcrema.spring_boot_toy.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = CustomTestConfiguration.class)
@AutoConfigureMockMvc
@ActiveProfiles(profiles = "test")
@Transactional
@Slf4j
public class OauthIntegrationTests {

    @Autowired
    private UserFixtureGenerator userFixtureGenerator;
    @Autowired
    private ChefFixtureGenerator chefFixtureGenerator;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("유저 토큰 로그인")
    public void testGetUserOauthToken() throws Exception{
        //given
        User user = userFixtureGenerator.generateUser();
        //when, then
        String accessToken = AccessTokenUtil.getAccessToken(mockMvc, user.getUsername(), UserFixtureGenerator.PASSWORD);
        log.info(accessToken);
        assertThat(accessToken).isNotBlank();
    }

    @Test
    @DisplayName("요리사 토큰 로그인")
    public void testGetChefOauthToken() throws Exception{
        //given
        Chef chef = chefFixtureGenerator.generateChef();
        //when, then
        String accessToken = AccessTokenUtil.getAccessToken(mockMvc, chef.getUsername(), ChefFixtureGenerator.PASSWORD);
        log.info(accessToken);
        assertThat(accessToken).isNotBlank();
    }

    @Test
    @DisplayName("허용되지 않은 url은 모두 제한한다")
    public void refuseAnyUrlNotAllowed() throws Exception {
        mockMvc.perform(get("api/forbidden-test"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
}
