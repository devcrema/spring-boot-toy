package devcrema.spring_boot_toy.service;

import devcrema.spring_boot_toy.admin.AdminRepository;
import devcrema.spring_boot_toy.chef.ChefRepository;
import devcrema.spring_boot_toy.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CheckDuplicatedEmailService {

    private final UserRepository userRepository;
    private final ChefRepository chefRepository;
    private final AdminRepository adminRepository;

    @Transactional(readOnly = true)
    public boolean checkDuplicatedEmail(String email){
        return userRepository.findByEmail(email).isPresent()
                || chefRepository.findByEmail(email).isPresent()
                || adminRepository.findByEmail(email).isPresent();
    }
}
