package com.example.newsspring.service;

import com.example.newsspring.exception.ApiRequestException;
import com.example.newsspring.model.User;
import com.example.newsspring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addNewUser(final User user) {
        Optional<User> optionalUser = userRepository.findUserByEmail(user.getEmail());
        if (optionalUser.isPresent()) {
            throw new ApiRequestException("Email " + user.getEmail() + " taken!");
        }
        userRepository.save(user);
    }

    public void deleteUser(final Long id) {
        if (!userRepository.existsById(id)) {
            throw new IllegalStateException("User with id " + id + " does not exists!");
        }
        userRepository.deleteById(id);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserByEmail(final String email) {
        return userRepository.findUserByEmail(email);
    }

    public Optional<User> getUserByLogin(final String login) {
        return userRepository.findUserByLogin(login);
    }

    @Transactional
    public void updateUserLoginPassword(final Long id, final String login, final String password) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("User with id " + id + " does not exists!"));
        if (login != null && login.trim().length() > 0 && !Objects.equals(user.getLogin(), login)) {
            Optional<User> userOptional = userRepository.findUserByLogin(login);
            if (userOptional.isPresent()) {
                throw new ApiRequestException("Email " + login + " taken!");
            }
            user.setLogin(login);
        }
        if (password != null && password.trim().length() > 0 && !Objects.equals(user.getPassword(), password)) {
            user.setPassword(password);
        }
    }

    @Transactional
    public void updateUserInfo(final Long id, final String email, final String firstName, final String lastName, final String country) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("User with id " + id + " does not exists!"));
        // TODO: validate data
        if (email != null && email.trim().length() > 0 && !Objects.equals(user.getEmail(), email)) {
            Optional<User> userOptional = userRepository.findUserByEmail(email);
            if (userOptional.isPresent()) {
                throw new ApiRequestException("Email " + email + " taken!");
            }
            user.setEmail(email);
        }
        if (firstName != null && firstName.trim().length() > 0 && !Objects.equals(user.getFirstName(), firstName)) {
            user.setFirstName(firstName);
        }
        if (lastName != null && lastName.trim().length() > 0 && !Objects.equals(user.getLastName(), lastName)) {
            user.setLastName(lastName);
        }
        if (country != null && country.trim().length() > 0 && !Objects.equals(user.getCountry(), country)) {
            user.setCountry(country);
        }
    }
}
