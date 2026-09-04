package com.greennest.backend.service;

import com.greennest.backend.dto.UserLoginRequest;
import com.greennest.backend.entity.User;

public interface UserService {
	
	User registerUser(User user);
    User loginUser(UserLoginRequest request);
}
