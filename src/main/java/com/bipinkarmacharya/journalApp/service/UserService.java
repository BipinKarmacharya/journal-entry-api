package com.bipinkarmacharya.journalApp.service;

import com.bipinkarmacharya.journalApp.entity.User;
import com.bipinkarmacharya.journalApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class UserService {

    @Autowired
    public UserRepository userRepository;

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> finUserById(Long userId) {
        return userRepository.findById(userId);
    }

    public void deleteUserById(Long userId) {
        userRepository.deleteById(userId);
    }
}
