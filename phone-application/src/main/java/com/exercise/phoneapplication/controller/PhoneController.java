package com.exercise.phoneapplication.controller;

import com.exercise.phoneapplication.PhoneApplication;
import com.exercise.phoneapplication.model.Phone;
import com.exercise.phoneapplication.repository.PhoneRepository;
import com.exercise.phoneapplication.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequestMapping("api/v1/phones")
@RestController
public class PhoneController {

    private final Logger logger = LoggerFactory.getLogger(PhoneApplication.class);

    private final PhoneRepository phoneRepository;
    private final UserRepository userRepository;

    public PhoneController(PhoneRepository phoneRepository, UserRepository userRepository) {
        this.phoneRepository = phoneRepository;
        this.userRepository = userRepository;
    }

    @GetMapping(path = "/{phoneId}")
    public ResponseEntity<String> getPhoneById(@PathVariable("phoneId") UUID uuid) {
        boolean userExists = phoneRepository.existsById(uuid);
        if (userExists){
            logger.info("Getting details of phone {}...", uuid);
            return new ResponseEntity<>(phoneRepository.findById(uuid).toString(), HttpStatus.OK);
        } else {
            logger.info("Phone with ID {} could not be found.", uuid);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Phone> addPhone(@RequestBody Phone phone) {
        logger.info("Received request to add phone: {}\n", phone.toString());
        return new ResponseEntity<>(phoneRepository.save(phone), HttpStatus.ACCEPTED);
    }

    @GetMapping()
    public ResponseEntity<?> getPhonesBelongingToUser(@RequestParam("belongsToUser") UUID uuid) {
        boolean userExists = userRepository.existsById(uuid);
        if (userExists){
            logger.info("Getting list of phones belonging to user {}...", uuid);
            List<Phone> phones = phoneRepository.findAll().stream()
                    .filter(phone -> phone.getBelongsToUser().equals(uuid))
                    .collect(Collectors.toList());
            return new ResponseEntity<>(phones, HttpStatus.OK);
        } else {
            logger.info("User with ID {} could not be found, unable to get phones.", uuid);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/{uuid}")
    public ResponseEntity<String> deletePhoneById(@PathVariable UUID uuid) {
        logger.info("Received request to delete phone {}", uuid);
        phoneRepository.deleteById(uuid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
