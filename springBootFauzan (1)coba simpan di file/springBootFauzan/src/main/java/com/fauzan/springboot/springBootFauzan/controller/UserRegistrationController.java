package com.fauzan.springboot.springBootFauzan.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fauzan.springboot.springBootFauzan.model.Role;
import com.fauzan.springboot.springBootFauzan.model.User;
import com.fauzan.springboot.springBootFauzan.service.UserService;
import com.fauzan.springboot.springBootFauzan.web.dto.UserRegistration;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

    private final UserService userService;
    private final BCryptPasswordEncoder pwencoder;

    @Autowired
    public UserRegistrationController(UserService userService, BCryptPasswordEncoder pwencoder) {
        this.userService = userService;
        this.pwencoder = pwencoder;
    }

    @ModelAttribute("user")
    public UserRegistration userRegistration() {
        return new UserRegistration();
    }

    @GetMapping
    public String showRegistrationForm() {
        return "registration";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") UserRegistration registration) {
        List<Role> roles = new ArrayList<>();
        Role userRole = new Role("ROLE_USER"); 
        roles.add(userRole);

        String encodedPassword = pwencoder.encode(registration.getPassword()); // Encode the password

        User user = new User(registration.getFirstName(), registration.getLastName(), registration.getEmail(),
                encodedPassword, roles);
       
        userService.save(user);
        return "redirect:/registration?success";
    }
}
