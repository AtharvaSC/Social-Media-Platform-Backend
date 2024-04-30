package com.oop.socialtwitter.controller;

import com.oop.socialtwitter.controller.dto.*;
import com.oop.socialtwitter.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    // Create a new comment
    @PostMapping
    public ResponseEntity<?> createComment(@RequestBody CommentRequest commentRequest) {
        String response = commentService.createComment(commentRequest);
        if (response.equals("Comment created successfully")) {
            return ResponseEntity.ok(response);
        } else {
            Map<String, String> objResponse = new HashMap<>();
            objResponse.put("Error", response);
            return ResponseEntity.status(HttpStatusCode.valueOf(404)).body(objResponse);
        }
    }

    // Retrieve an existing comment
    @GetMapping
    public ResponseEntity<?> getComment(@RequestParam int commentID) {
        CommentResponse comment = commentService.getComment(commentID);

        if (comment != null) {
            return ResponseEntity.ok(comment);
        } else {
            Map<String, String> objResponse = new HashMap<>();
            objResponse.put("Error", "Comment does not exist");
            return ResponseEntity.status(HttpStatusCode.valueOf(404)).body(objResponse);
        }
    }

    // Edit an existing comment
    @PatchMapping
    public ResponseEntity<?> editComment(@RequestBody CommentEdit commentEdit) {
        String response = commentService.editComment(commentEdit);
        if (response.equals("Comment edited successfully")) {
            return ResponseEntity.ok(response);
        } else {
            Map<String, String> objResponse = new HashMap<>();
            objResponse.put("Error", response);
            return ResponseEntity.status(HttpStatusCode.valueOf(404)).body(objResponse);
        }
    }

    // Delete an existing comment
    @DeleteMapping
    public ResponseEntity<?> deleteComment(@RequestParam int commentID) {
        String response = commentService.deleteComment(commentID);
        if (response.equals("Comment deleted")) {
            return ResponseEntity.ok(response);
        } else {
            Map<String, String> objResponse = new HashMap<>();
            objResponse.put("Error", response);
            return ResponseEntity.status(HttpStatusCode.valueOf(404)).body(objResponse);
        }
    }
}
