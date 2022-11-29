
package ru.dorjik.rest_light.configs;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.dorjik.rest_light.model.Role;
import ru.dorjik.rest_light.model.User;
import ru.dorjik.rest_light.repository.RoleRepository;
import ru.dorjik.rest_light.service.UserService;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;


@Component
public class DefaultUser {

    UserService userService;
    PasswordEncoder bCryptPasswordEncoder;
    RoleRepository roleRepository;

    public DefaultUser(UserService userService, PasswordEncoder bCryptPasswordEncoder, RoleRepository roleRepository) {
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.roleRepository = roleRepository;
    }

    @PostConstruct
    void init() {
        Role userRole = new Role("ROLE_ADMIN");
        Role adminRole = new Role("ROLE_USER");
        roleRepository.save(userRole);
        roleRepository.save(adminRole);
        Set<Role> roles = new HashSet<>();
        roles.add(userRole);
        roles.add(adminRole);
        User user = new User("admin", "admin", "admin@mail.ru", 33, roles);
        userService.addUser(user);
    }
}



