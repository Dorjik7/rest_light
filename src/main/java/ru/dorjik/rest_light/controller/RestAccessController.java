
package ru.dorjik.rest_light.controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.dorjik.rest_light.model.User;
import ru.dorjik.rest_light.service.UserService;


import java.util.List;

@RestController
@RequestMapping("/api")
public class RestAccessController {

    private final UserService userService;

    public RestAccessController(UserService userService) {
        this.userService = userService;
    }

      @GetMapping("/users")
      public ResponseEntity<List<User>> getAllUsers() {
        List<User> userList = userService.getListUsers();
       if (userList != null && !userList.isEmpty()) {
            return new ResponseEntity<>(userList, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.findUserById(id);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/createUser")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        System.out.println(user);
        userService.addUser(user);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/userMain")
    public User showUserInfo(Authentication authentication) {
        return (User) authentication.getPrincipal();
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        userService.updateUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<User> removeUser(@PathVariable("id") Long id) {
        userService.removeUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}



