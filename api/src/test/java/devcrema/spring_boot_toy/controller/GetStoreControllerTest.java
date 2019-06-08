package devcrema.spring_boot_toy.controller;

import devcrema.spring_boot_toy.Api;
import devcrema.spring_boot_toy.CustomObjectMapper;
import devcrema.spring_boot_toy.CustomTestConfiguration;
import devcrema.spring_boot_toy.store.StoreRepository;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = CustomTestConfiguration.class)
@AutoConfigureMockMvc
@ActiveProfiles(profiles = "test")
@Slf4j
class GetStoreControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private StoreRepository storeRepository;

    @Test
    @DisplayName("음식점 목록을 페이징해서 불러옴")
    public void loadAllStoresWithPaging() throws Exception {
        //given
        String url = Api.Store.STORES;
        //when
        mockMvc.perform(get(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .param("page", "0")
                .param("size", "4"))
                .andDo(print())
                .andExpect(status().isOk());
        //then
        verify(storeRepository).findAllBy(any());
    }


}