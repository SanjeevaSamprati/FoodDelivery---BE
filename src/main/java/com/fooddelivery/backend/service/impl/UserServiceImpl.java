package com.fooddelivery.backend.service.impl;

import com.fooddelivery.backend.entity.User;
import com.fooddelivery.backend.repository.UserRepository;
import com.fooddelivery.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

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
               .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }
    
    @Override
    public String deleteById(Long id) {
    	userRepository.deleteById(id);
    	return "User deleted";
    }

}
