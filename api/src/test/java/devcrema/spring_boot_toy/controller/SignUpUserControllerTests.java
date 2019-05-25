package devcrema.spring_boot_toy.controller;

import devcrema.spring_boot_toy.CustomObjectMapper;
import devcrema.spring_boot_toy.CustomTestConfiguration;
import devcrema.spring_boot_toy.ErrorResponse;
import devcrema.spring_boot_toy.user.DuplicatedEmailException;
import devcrema.spring_boot_toy.user.SignUpUserRequest;
import devcrema.spring_boot_toy.user.service.SignUpUserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static devcrema.spring_boot_toy.test_fixture.UserFixtureGenerator.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.willThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = CustomTestConfiguration.class)
@AutoConfigureMockMvc
@ActiveProfiles(profiles = "test")
@Slf4j
public class SignUpUserControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CustomObjectMapper objectMapper;

    @MockBean
    private SignUpUserService signUpUserService;

    @Test
    @DisplayName("정상적으로 가입 요청하면 OK")
    public void okIfRequestedToSignUpNormally() throws Exception {
        //given
        String url = "/api/users";
        SignUpUserRequest signUpUserRequest = new SignUpUserRequest(
                EMAIL,
                NICKNAME,
                PASSWORD);
        //when, then
        mockMvc.perform(post(url)
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .content(objectMapper.writeValueAsString(signUpUserRequest)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("비정상적인 정보로 가입요청하면 BAD_REQUEST")
    public void badRequestIfRequestedSignUpWithAbnormalInformation() throws Exception {
        //given
        String url = "/api/users";
        SignUpUserRequest signUpUserRequest = new SignUpUserRequest(
                EMAIL,
                NICKNAME,
                "1234");
        //when
        MvcResult mvcResult = mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(signUpUserRequest)))
                .andDo(print())
                .andExpect(status().isBadRequest()).andReturn();
        //then
        ErrorResponse errorResponse = objectMapper.map(mvcResult.getResponse().getContentAsString());
        log.info(errorResponse.toString());
        assertThat(errorResponse.getCode()).isEqualTo(ErrorResponse.ErrorType.BAD_REQUEST.getCode());
    }

    @Test
    @DisplayName("중복된 이메일로 가입하면 CONFLICT")
    public void conflictIfRequestedSignUpWithDuplicatedEmail() throws Exception {
        //given
        String url = "/api/users";
        SignUpUserRequest signUpUserRequest = new SignUpUserRequest(
                EMAIL,
                NICKNAME,
                PASSWORD);
        willThrow(new DuplicatedEmailException()).given(signUpUserService).signUp(any());
        //when
        MvcResult mvcResult = mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(signUpUserRequest)))
                .andDo(print())
                .andExpect(status().isConflict()).andReturn();
        //then
        ErrorResponse errorResponse = objectMapper.map(mvcResult.getResponse().getContentAsString());
        log.info(errorResponse.toString());
        assertThat(errorResponse.getCode()).isEqualTo(ErrorResponse.ErrorType.DUPLICATED_EMAIL.getCode());
    }
}
