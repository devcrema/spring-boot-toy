package devcrema.spring_boot_toy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class SpringBootToyApplicationTests {

    @Test
    @DisplayName("컨텍스트가 로드 되는지")
    public void contextLoads() {
    }

}
