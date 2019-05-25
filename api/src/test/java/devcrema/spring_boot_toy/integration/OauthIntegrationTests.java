package devcrema.spring_boot_toy.integration;

import devcrema.spring_boot_toy.AccessTokenUtil;
import devcrema.spring_boot_toy.CustomTestConfiguration;
import devcrema.spring_boot_toy.test_fixture.UserFixtureGenerator;
import devcrema.spring_boot_toy.user.User;
import devcrema.spring_boot_toy.service.CustomPasswordEncoder;
import devcrema.spring_boot_toy.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
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

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = CustomTestConfiguration.class)
@AutoConfigureMockMvc
@ActiveProfiles(profiles = "test")
@Transactional
@Slf4j
public class OauthIntegrationTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomPasswordEncoder customPasswordEncoder;

    @Autowired
    private UserFixtureGenerator userFixtureGenerator;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetOauthToken() throws Exception{
        //given
        User user = userFixtureGenerator.generateUser();
        //when, then
        String accessToken = AccessTokenUtil.getAccessToken(mockMvc, user.getUsername(), UserFixtureGenerator.PASSWORD);
        log.info(accessToken);
        assertThat(accessToken).isNotBlank();
    }
}
