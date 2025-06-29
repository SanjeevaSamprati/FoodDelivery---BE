package com.fooddelivery.backend.service;

import java.util.List;

import com.fooddelivery.backend.entity.User;

public interface UserService {
	User saveUser(User user);
	List<User> getAllUsers();
}
