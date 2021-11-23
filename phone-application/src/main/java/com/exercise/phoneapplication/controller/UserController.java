package com.exercise.phoneapplication.controller;

import com.exercise.phoneapplication.PhoneApplication;
import com.exercise.phoneapplication.model.User;
import com.exercise.phoneapplication.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("api/v1/users")
@RestController
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(PhoneApplication.class);

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    List<User> all() {
        return userRepository.findAll();
    }

    @GetMapping(path = "{userId}")
    public User getUserById(@PathVariable("userId") UUID uuid) {
        return userRepository.findById(uuid).orElse(null);
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public User addUser(@RequestBody User user) {
        logger.info("Received request to add user: \n" + user.toString());
        return userRepository.save(user);
    }

}
