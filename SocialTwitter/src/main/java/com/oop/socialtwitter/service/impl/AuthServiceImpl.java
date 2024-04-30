//AuthServiceImpl.java
package com.oop.socialtwitter.service.impl;

import com.oop.socialtwitter.controller.dto.LoginRequest;
import com.oop.socialtwitter.controller.dto.SignupRequest;
import com.oop.socialtwitter.model.User;
import com.oop.socialtwitter.repository.UserRepository;
import com.oop.socialtwitter.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public String login(LoginRequest loginRequest) {
        User user = userRepository.findByEmail(loginRequest.getEmail());
        if (user != null && user.getPassword().equals(loginRequest.getPassword())) {
            return "Login Successful";
        }
        else if(user==null){
            return "User does not exist";
        }else {
            return "Username/Password Incorrect";
        }
    }

    @Override
    public String signup(SignupRequest signupRequest) {
        if (userRepository.findByEmail(signupRequest.getEmail()) != null) {
            return "Forbidden, Account already exists";
        } else {
            User user = new User();
            user.setEmail(signupRequest.getEmail());
            user.setName(signupRequest.getName());
            user.setPassword(signupRequest.getPassword());
            userRepository.save(user);
            return "Account Creation Successful";
        }
    }
}
