package devcrema.spring_boot_toy.test_fixture;

import devcrema.spring_boot_toy.chef.Chef;
import devcrema.spring_boot_toy.chef.ChefRepository;
import devcrema.spring_boot_toy.service.CustomPasswordEncoder;
import devcrema.spring_boot_toy.user.User;
import devcrema.spring_boot_toy.user.repository.UserRepository;
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
        Optional<Chef> optionalChef = chefRepository.findChefByEmail(EMAIL);
        return optionalChef.orElseGet(()
                -> chefRepository.save(buildTestChefVo().initialize(customPasswordEncoder)));
    }

    public static Chef buildTestChefVo() {
        return Chef.builder()
                .email(EMAIL)
                .nickname(NICKNAME)
                .password(PASSWORD)
                .build();
    }
}
