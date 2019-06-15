package devcrema.spring_boot_toy.chef;

import devcrema.spring_boot_toy.service.CheckDuplicatedEmailService;
import devcrema.spring_boot_toy.service.CustomPasswordEncoder;
import devcrema.spring_boot_toy.user.DuplicatedEmailException;
import devcrema.spring_boot_toy.user.User;
import devcrema.spring_boot_toy.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class SignUpChefService {

    private final ChefRepository chefRepository;
    private final CustomPasswordEncoder customPasswordEncoder;
    private final CheckDuplicatedEmailService checkDuplicatedEmailService;

    public void signUp(Chef chef) {
        if(checkDuplicatedEmailService.checkDuplicatedEmail(chef.getEmail())) {
            throw new DuplicatedEmailException("중복된 이메일이 있습니다.");
        } else {
            chef.initialize(customPasswordEncoder);
            chefRepository.save(chef);
        }
    }
}
