package com.oop.socialtwitter.service.impl;

import com.oop.socialtwitter.controller.dto.UserResponse;
import com.oop.socialtwitter.model.User;
import com.oop.socialtwitter.repository.UserRepository;
import com.oop.socialtwitter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserResponse> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(this::convertToUserResponse)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponse getUserDetail(int userID) {
        User user = userRepository.findById(userID).orElse(null);
        if (user != null) {
            return convertToUserResponse(user);
        } else {
            return null;
        }
    }

    private UserResponse convertToUserResponse(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setName(user.getName());
        userResponse.setUserID(user.getId());
        userResponse.setEmail(user.getEmail());
        return userResponse;
    }
}
