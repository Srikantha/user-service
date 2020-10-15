package com.ing.assignment.user.service;

import com.ing.assignment.user.model.User;
import com.ing.assignment.user.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CircuitBreakerFactory circuitBreakerFactory;

    public List<User> getAllUsers() {
        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("circuitbreaker");

        return circuitBreaker.run(() -> userRepository.findAll());
    }

    public Optional<User> getUserById(Long userId) {
        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("circuitbreaker");

        return circuitBreaker.run(() -> userRepository.findById(userId));
    }

    @Transactional
    public User createUser(User user) {
        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("circuitbreaker");
        return circuitBreaker.run(() -> userRepository.save(user));
    }

    @Transactional
    public Optional<User> updateUser(Long userId, User userDetails) {
        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("circuitbreaker");
        return circuitBreaker.run(() -> {
                    Optional<User> userOptional = userRepository.findById(userId);
                    if(userOptional.isPresent()) {
                        User user = userOptional.get();
                        user.setEmailId(userDetails.getEmailId());
                        user.setLastName(userDetails.getLastName());
                        user.setFirstName(userDetails.getFirstName());
                        User savedUser = userRepository.save(user);
                        return Optional.of(savedUser);
                    } else {
                        return Optional.empty();
                    }
        });
    }

    @Transactional
    public Optional<User> deleteUser(Long userId) {

        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("circuitbreaker");
        return circuitBreaker.run(() -> {
            Optional<User> userOptional = userRepository.findById(userId);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                userRepository.delete(user);
                return Optional.of(user);
            } else {
                return Optional.empty();
            }
        });
    }
}
