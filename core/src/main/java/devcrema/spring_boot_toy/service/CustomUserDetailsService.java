package devcrema.spring_boot_toy.service;

import devcrema.spring_boot_toy.admin.AdminRepository;
import devcrema.spring_boot_toy.chef.ChefRepository;
import devcrema.spring_boot_toy.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final ChefRepository chefRepository;
    private final AdminRepository adminRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseGet(()
                -> chefRepository.findByEmail(email).orElseGet(()
                -> adminRepository.findByEmail(email).orElseThrow(()
                -> new UsernameNotFoundException(email + " 해당 유저를 찾을 수 없습니다."))));
    }
}
