package ru.dorjik.rest_light.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dorjik.rest_light.model.Role;



@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByRolename(String name);
}
