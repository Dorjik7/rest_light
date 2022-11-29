package ru.dorjik.rest_light.service;
import org.springframework.security.core.userdetails.UserDetailsService;
import ru.dorjik.rest_light.model.User;


import java.util.List;

public interface UserService extends UserDetailsService{

    void addUser(User user);

    void updateUser(User user);

    void removeUser(Long id);

    User findUserById(Long id);

    List<User> getListUsers();
}
