package ru.dorjik.rest_light.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dorjik.rest_light.model.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @EntityGraph(value = "User.roles")
    User findUserByUsername(String username);
}
