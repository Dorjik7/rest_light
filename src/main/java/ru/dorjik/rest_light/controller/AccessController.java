
package ru.dorjik.rest_light.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.dorjik.rest_light.model.User;
import ru.dorjik.rest_light.service.RoleService;
import ru.dorjik.rest_light.service.UserService;

import java.security.Principal;


@Controller
@RequestMapping ("/")
public class AccessController {

    private final UserService userService;
    private final RoleService roleService;



    public AccessController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/admin")
    public String getAllUsers(Model model, @AuthenticationPrincipal User currentUser) {

        model.addAttribute("createUser", new User());
        model.addAttribute("userList", userService.getListUsers());
        model.addAttribute("roleList", roleService.getAllRoles());
        model.addAttribute("currentUser", currentUser);
        return "admin";
    }
    @GetMapping("/admin/edit/{id}")
    public String findUser(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.findUserById(id));
        return "admin";
    }
    @GetMapping("/user")
    public String getUser(Model model, Principal principal) {
        User user = (User) userService.loadUserByUsername(principal.getName());
        model.addAttribute("user", user);
        return "/user";
    }
}





