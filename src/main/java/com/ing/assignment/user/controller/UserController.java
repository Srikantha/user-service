package com.ing.assignment.user.controller;

import com.ing.assignment.user.exception.ResourceNotFoundException;
import com.ing.assignment.user.model.User;
import com.ing.assignment.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/userdetails",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping(value = "/userdetails/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUserById(@PathVariable(value = "id") @Valid @Pattern(regexp = "[0-9]")Long userId) {
        Optional<User> user = userService.getUserById(userId);
        if(user.isPresent()) {
            return ResponseEntity.ok().body(user.get());
        } else {
            return new ResponseEntity(String.format("User not found for ID:%s", userId.longValue()), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/userdetails/restricted")
    public User createUser(@Valid @RequestBody User user) {
        return userService.createUser(user);
    }

    @PutMapping(value = "/userdetails/restricted/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> updateUser(@PathVariable(value = "id") Long userId,
                                           @Valid @RequestBody User userDetails) throws ResourceNotFoundException {
        Optional<User> userOptional = userService.getUserById(userId);
        if(userOptional.isPresent()) {
            User user = userOptional.get();
            user.setEmailId(userDetails.getEmailId());
            user.setLastName(userDetails.getLastName());
            user.setFirstName(userDetails.getFirstName());
            final Optional<User> updatedUser = userService.updateUser(userId, user);
            return ResponseEntity.ok(updatedUser.get());
        } else {
            return new ResponseEntity(String.format("User not found for ID:%s", userId.longValue()), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/userdetails/restricted/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> deleteUser(@PathVariable(value = "id") Long userId) {
        Optional<User> userOptional = userService.getUserById(userId);
        if(userOptional.isPresent()) {
            User user = userOptional.get();
            return ResponseEntity.ok(user);
        } else {
            return new ResponseEntity(String.format("User not found for ID:%s", userId.longValue()), HttpStatus.NOT_FOUND);
        }
    }
}
