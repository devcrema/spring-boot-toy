package devcrema.spring_boot_toy.user.service;

import devcrema.spring_boot_toy.config.AuthorityManager;
import devcrema.spring_boot_toy.user.DuplicatedEmailException;
import devcrema.spring_boot_toy.user.User;
import devcrema.spring_boot_toy.user.UserPasswordEncoder;
import devcrema.spring_boot_toy.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SignUpUserService {

    private final UserRepository userRepository;
    private final UserPasswordEncoder userPasswordEncoder;
    private final AuthorityManager authorityManager;

    @Transactional
    public void signUp(User user) {

        Optional<User> duplicatedUser = userRepository.findByEmail(user.getEmail());

        if(duplicatedUser.isPresent()) {
            throw new DuplicatedEmailException("중복된 이메일이 있습니다.");
        } else {
            user.initialize(userPasswordEncoder, Collections.singletonList(authorityManager.findRole(AuthorityManager.RoleType.ROLE_USER)));
            userRepository.save(user);
        }
    }
}
