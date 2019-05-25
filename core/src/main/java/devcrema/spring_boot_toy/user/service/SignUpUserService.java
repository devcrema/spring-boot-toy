package devcrema.spring_boot_toy.user.service;

import devcrema.spring_boot_toy.service.CheckDuplicatedEmailService;
import devcrema.spring_boot_toy.user.DuplicatedEmailException;
import devcrema.spring_boot_toy.user.User;
import devcrema.spring_boot_toy.service.CustomPasswordEncoder;
import devcrema.spring_boot_toy.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SignUpUserService {

    private final UserRepository userRepository;
    private final CustomPasswordEncoder customPasswordEncoder;
    private final CheckDuplicatedEmailService checkDuplicatedEmailService;

    @Transactional
    public void signUp(User user) {
        if(checkDuplicatedEmailService.checkDuplicatedEmail(user.getEmail())) {
            throw new DuplicatedEmailException("중복된 이메일이 있습니다.");
        } else {
            user.initialize(customPasswordEncoder);
            userRepository.save(user);
        }
    }
}
