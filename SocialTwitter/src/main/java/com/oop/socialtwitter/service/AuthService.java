//AuthService.java
package com.oop.socialtwitter.service;

import com.oop.socialtwitter.controller.dto.LoginRequest;
import com.oop.socialtwitter.controller.dto.SignupRequest;

public interface AuthService {
    String login(LoginRequest loginRequest);
    String signup(SignupRequest signupRequest);
}
