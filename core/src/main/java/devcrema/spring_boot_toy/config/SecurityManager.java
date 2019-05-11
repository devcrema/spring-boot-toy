package devcrema.spring_boot_toy.config;

import devcrema.spring_boot_toy.user.Privilege;
import devcrema.spring_boot_toy.user.PrivilegeRepository;
import devcrema.spring_boot_toy.user.Role;
import devcrema.spring_boot_toy.user.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SecurityManager implements ApplicationListener<ContextRefreshedEvent> {

    public static final String CHEF_PRIVILEGE = "CHEF_PRIVILEGE";
    public static final String USER_PRIVILEGE = "USER_PRIVILEGE";
    public static final String ADMIN_PRIVILEGE = "ADMIN_PRIVILEGE";

    public static final String ROLE_CHEF = "ROLE_CHEF";
    public static final String ROLE_USER = "ROLE_USER";
    public static final String ROLE_ADMIN = "ROLE_ADMIN";

    private boolean alreadySetup = false;

    private final RoleRepository roleRepository;

    private final PrivilegeRepository privilegeRepository;


    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (alreadySetup) return;

        Privilege chefPrivilege = createPrivilegeIfNotFound(CHEF_PRIVILEGE);
        Privilege userPrivilege = createPrivilegeIfNotFound(USER_PRIVILEGE);
        Privilege adminPrivilege = createPrivilegeIfNotFound(ADMIN_PRIVILEGE);

        List<Privilege> adminPrivileges = Arrays.asList(
                chefPrivilege, userPrivilege, adminPrivilege);

        createRoleIfNotFound(ROLE_CHEF, Collections.singletonList(chefPrivilege));
        createRoleIfNotFound(ROLE_USER, Collections.singleton(userPrivilege));
        createRoleIfNotFound(ROLE_ADMIN, adminPrivileges);

        alreadySetup = true;
    }

    private Privilege createPrivilegeIfNotFound(String name) {

        return privilegeRepository.findByName(name)
                .orElseGet(() -> privilegeRepository.save(new Privilege(name)));
    }

    private void createRoleIfNotFound(String name, Collection<Privilege> privileges) {

        roleRepository.findByName(name).orElseGet(() -> {
            Role role = new Role(name);
            role.setPrivileges(privileges);
            return roleRepository.save(role);
        });
    }
}
