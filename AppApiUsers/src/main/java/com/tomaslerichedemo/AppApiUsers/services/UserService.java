package com.tomaslerichedemo.AppApiUsers.services;

import com.tomaslerichedemo.AppApiUsers.shared.UsersDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UsersDto createUser(UsersDto userDetails);
    UsersDto getUserDetailsByEmail(String email);
}
