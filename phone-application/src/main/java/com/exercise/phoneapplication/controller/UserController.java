package com.exercise.phoneapplication.controller;

import com.exercise.phoneapplication.PhoneApplication;
import com.exercise.phoneapplication.model.User;
import com.exercise.phoneapplication.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequestMapping("api/v1/users")
@RestController
public class UserController {

    private final String NOT_FOUND = "User with ID {} could not be found, unable to get phones.";

    private final Logger logger = LoggerFactory.getLogger(PhoneApplication.class);

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable("userId") UUID uuid) {
        boolean userExists = userRepository.existsById(uuid);
        if (userExists){
            logger.info("Getting details of user {}...", uuid);
            return new ResponseEntity<>(userRepository.findById(uuid), HttpStatus.OK);
        } else {
            logger.info(NOT_FOUND, uuid);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<User> addUser(@RequestBody User user) {
        logger.info("Received request to add user: {}\n", user.toString());
        return new ResponseEntity<>(userRepository.save(user), HttpStatus.ACCEPTED);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<?> updateUserPhoneNumber(@PathVariable UUID uuid,
                                                   @RequestParam(name="preferredPhoneNumber") String phoneNumber) {
        logger.info("Received request to update phone number of user {} to {}", uuid, phoneNumber);
        Optional<User> possibleUser = userRepository.findById(uuid);
        if (possibleUser.isPresent()){
            User user = possibleUser.get();
            user.setPreferredPhoneNumber(phoneNumber);
            return new ResponseEntity<>(userRepository.save(user), HttpStatus.ACCEPTED);
        } else {
            logger.info(NOT_FOUND, uuid);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "{uuid}")
    public ResponseEntity<String> deleteUserById(@PathVariable UUID uuid) {
        logger.info("Received request to delete user {}", uuid);
        userRepository.deleteById(uuid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
