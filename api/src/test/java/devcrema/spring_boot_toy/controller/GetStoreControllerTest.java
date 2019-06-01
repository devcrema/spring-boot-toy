package devcrema.spring_boot_toy.controller;

import devcrema.spring_boot_toy.CustomObjectMapper;
import devcrema.spring_boot_toy.CustomTestConfiguration;
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

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = CustomTestConfiguration.class)
@AutoConfigureMockMvc
@ActiveProfiles(profiles = "test")
@Slf4j
class GetStoreControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CustomObjectMapper objectMapper;

    @Test
    @DisplayName("음식점 목록을 페이징해서 불러옴")
    public void loadAllStoresWithPaging(){
        //TODO 구현 
    }

}