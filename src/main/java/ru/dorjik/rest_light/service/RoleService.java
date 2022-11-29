package ru.dorjik.rest_light.service;


import org.springframework.stereotype.Service;
import ru.dorjik.rest_light.model.Role;


import java.util.Set;

@Service
public interface RoleService {

    void addRole(Role role);


    Role getRoleByName(String name);

    Set<Role> getRolesByName(Set<Role> roles);


    Set<Role> getAllRoles();
}