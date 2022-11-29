package ru.dorjik.rest_light.service;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.dorjik.rest_light.model.User;
import ru.dorjik.rest_light.repository.RoleRepository;
import ru.dorjik.rest_light.repository.UserRepository;


import javax.transaction.Transactional;
import java.util.List;

@Service
public class  UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private   final PasswordEncoder passwordEncoder;


    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, @Lazy PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        if (!user.getPassword().equals(userRepository.getById(user.getId()).getPassword())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void removeUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.getById(id);
    }

    @Override
    public List<User> getListUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findUserByUsername(username);
        return user;
    }

}

