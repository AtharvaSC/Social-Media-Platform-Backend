//AuthController.java
package com.oop.socialtwitter.controller;

import com.oop.socialtwitter.controller.dto.LoginRequest;
import com.oop.socialtwitter.controller.dto.SignupRequest;
import com.oop.socialtwitter.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        String response = authService.login(loginRequest);
        if (response.equals("Login Successful")) {
            return ResponseEntity.ok(response);
        } else {
            Map<String, String> objResponse = new HashMap<>();
            objResponse.put("Error", response);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(objResponse);
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest signupRequest) {
        String response = authService.signup(signupRequest);
        if (response.equals("Account Creation Successful")) {
            return ResponseEntity.ok(response);
        } else {
            Map<String, String> objResponse = new HashMap<>();
            objResponse.put("Error", response);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(objResponse);
        }
    }
}
