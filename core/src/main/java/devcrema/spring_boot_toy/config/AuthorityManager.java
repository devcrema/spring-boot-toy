package devcrema.spring_boot_toy.config;

import devcrema.spring_boot_toy.user.*;
import devcrema.spring_boot_toy.user.repository.PrivilegeRepository;
import devcrema.spring_boot_toy.user.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class AuthorityManager {

    public enum RoleType {
        ROLE_CHEF,
        ROLE_USER,
        ROLE_ADMIN
    }

    public enum PrivilegeType {
        CHEF_PRIVILEGE,
        USER_PRIVILEGE,
        ADMIN_PRIVILEGE
    }

    private final RoleRepository roleRepository;

    private final PrivilegeRepository privilegeRepository;

    @Transactional
    @EventListener(ApplicationReadyEvent.class)
    public void initializeAuthority() {

        Privilege chefPrivilege = createPrivilegeIfNotFound(PrivilegeType.CHEF_PRIVILEGE);
        Privilege userPrivilege = createPrivilegeIfNotFound(PrivilegeType.USER_PRIVILEGE);
        Privilege adminPrivilege = createPrivilegeIfNotFound(PrivilegeType.ADMIN_PRIVILEGE);

        List<Privilege> adminPrivileges = Arrays.asList(
                chefPrivilege, userPrivilege, adminPrivilege);

        createRoleIfNotFound(RoleType.ROLE_CHEF, Collections.singletonList(chefPrivilege));
        createRoleIfNotFound(RoleType.ROLE_USER, Collections.singleton(userPrivilege));
        createRoleIfNotFound(RoleType.ROLE_ADMIN, adminPrivileges);
        log.info("authority is successfully initialized");
    }

    @Transactional
    public Privilege createPrivilegeIfNotFound(PrivilegeType privilegeType) {

        return privilegeRepository.findByName(privilegeType.name())
                .orElseGet(() -> privilegeRepository.save(new Privilege(privilegeType.name())));
    }

    private void createRoleIfNotFound(RoleType roleType, Collection<Privilege> privileges) {

        roleRepository.findByName(roleType.name()).orElseGet(() -> {
            Role role = new Role(roleType.name());
            role.setPrivileges(privileges);
            return roleRepository.save(role);
        });
    }

    @Transactional(readOnly = true)
    public Role findRole(RoleType roleType) {
        return roleRepository.findByName(roleType.name()).orElseThrow(()->new RoleNotFoundException("해당 role이 존재하지 않습니다."));
    }
}
