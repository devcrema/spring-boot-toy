package devcrema.spring_boot_toy.test_fixture;

import devcrema.spring_boot_toy.chef.Chef;
import devcrema.spring_boot_toy.chef.ChefRepository;
import devcrema.spring_boot_toy.service.CustomPasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ChefFixtureGenerator {
    public static final String EMAIL = "chef@test.com";
    public static final String NICKNAME = "chefName";
    public static final String PASSWORD = "chefPassword";

    private final ChefRepository chefRepository;
    private final CustomPasswordEncoder customPasswordEncoder;

    public Chef generateChef() {
        return generateChef(EMAIL, NICKNAME);
    }

    public Chef generateChef(String email, String nickname){
        Optional<Chef> optionalChef = chefRepository.findChefByEmail(email);
        return optionalChef.orElseGet(()
                -> chefRepository.save(buildTestChefVo(email, nickname).initialize(customPasswordEncoder)));
    }

    public static Chef buildTestChefVo() {
        return buildTestChefVo(EMAIL, NICKNAME);
    }

    public static Chef buildTestChefVo(String email, String nickname) {
        return Chef.builder()
                .email(email)
                .nickname(nickname)
                .password(PASSWORD)
                .build();
    }


}
