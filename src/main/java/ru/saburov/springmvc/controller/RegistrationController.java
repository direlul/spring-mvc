package ru.saburov.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.saburov.springmvc.domain.Role;
import ru.saburov.springmvc.domain.User;
import ru.saburov.springmvc.repository.UserRepository;

import java.util.Collections;

@Controller
public class RegistrationController {
    private final UserRepository userRepository;

    public RegistrationController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@ModelAttribute User user, Model model) {
        User userFromDb = userRepository.findByUsername(user.getUsername());

        if (userFromDb != null) {
            model.addAttribute("message", "User exists!");
            return "registration";
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepository.save(user);

        return "redirect:/login";
    }
}
