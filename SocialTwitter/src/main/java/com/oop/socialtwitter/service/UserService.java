package com.oop.socialtwitter.service;

import com.oop.socialtwitter.controller.dto.UserResponse;

import java.util.List;

public interface UserService {
    List<UserResponse> getAllUsers();
    UserResponse getUserDetail(int userID);
}
