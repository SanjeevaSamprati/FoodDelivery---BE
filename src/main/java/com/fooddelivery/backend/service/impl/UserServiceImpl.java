package com.fooddelivery.backend.service.impl;

import com.fooddelivery.backend.dto.LoginDTO;
import com.fooddelivery.backend.entity.User;
import com.fooddelivery.backend.exception.ResourceNotFoundException;
import com.fooddelivery.backend.repository.UserRepository;
import com.fooddelivery.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User saveUser(User user) {
    	String encodedPassword = passwordEncoder.encode(user.getPassword());
    	user.setPassword(encodedPassword);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    
    @Override
    public User getById(Long id) {
        return userRepository.findById(id)
               .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id + " Not found"));
    }
    
    @Override
    public String deleteById(Long id) {
    	userRepository.deleteById(id);
    	return "User deleted";
    }
    
    @Override
    public String loginUser(LoginDTO loginDTO) {
        // Step 1: Get user by email
        User user = userRepository.findByEmail(loginDTO.getEmail()).orElseThrow(() -> new ResourceNotFoundException("No User found with email  " + loginDTO.getEmail()));

        // Step 2: Match password
		boolean isPasswordMatch = passwordEncoder.matches(loginDTO.getPassword(), user.getPassword());

        if (!isPasswordMatch) {
            return "Incorrect password";
        }

        return "Login successful";
    }

}
