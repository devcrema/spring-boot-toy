package devcrema.spring_boot_toy.user;

import devcrema.spring_boot_toy.config.AuthorityManager;
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
    public void signUp(User user) throws DuplicatedEmailException {

        Optional<User> duplicatedUser = userRepository.findByEmail(user.getEmail());

        if(duplicatedUser.isPresent()) {
            throw new DuplicatedEmailException("중복된 이메일입니다.");
        } else {
            user.initialize(userPasswordEncoder, Collections.singletonList(authorityManager.findRole(AuthorityManager.RoleType.ROLE_USER)));
            userRepository.save(user);
        }
    }
}
