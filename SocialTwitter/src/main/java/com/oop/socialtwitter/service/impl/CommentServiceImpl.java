// CommentServiceImpl.java
package com.oop.socialtwitter.service.impl;

import com.oop.socialtwitter.controller.dto.*;
import com.oop.socialtwitter.model.Comment;
import com.oop.socialtwitter.model.Post;
import com.oop.socialtwitter.model.User;
import com.oop.socialtwitter.repository.CommentRepository;
import com.oop.socialtwitter.repository.PostRepository;
import com.oop.socialtwitter.repository.UserRepository;
import com.oop.socialtwitter.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public String createComment(CommentRequest commentRequest) {
        Optional<User> userOptional = userRepository.findById(commentRequest.getUserID());
        if (userOptional.isPresent()) {
            Optional<Post> postOptional = postRepository.findById(commentRequest.getPostID());
            if (postOptional.isPresent()) {
                Comment comment = new Comment();
                comment.setCommentBody(commentRequest.getCommentBody());
                comment.setPost(postOptional.get());
                comment.setUser(userOptional.get());
                commentRepository.save(comment);
                return "Comment created successfully";
            } else {
                return "Post does not exist";
            }
        } else {
            return "User does not exist";
        }
    }

    @Override
    public CommentResponse getComment(int commentID) {
        Optional<Comment> commentOptional = commentRepository.findById(commentID);
        if (commentOptional.isPresent()) {
            Comment comment = commentOptional.get();
            CommentResponse response = new CommentResponse();
            response.setCommentID(comment.getId());
            response.setCommentBody(comment.getCommentBody());
            CommentCreator creator = new CommentCreator();
            creator.setUserID(comment.getUser().getId());
            creator.setName(comment.getUser().getName());
            response.setCommentCreator(creator);
            return response;
        } else {
            return null;
        }
    }

    @Override
    public String editComment(CommentEdit commentEdit) {
        Optional<Comment> commentOptional = commentRepository.findById(commentEdit.getCommentID());
        if (commentOptional.isPresent()) {
            Comment comment = commentOptional.get();
            comment.setCommentBody(commentEdit.getCommentBody());
            commentRepository.save(comment);
            return "Comment edited successfully";
        } else {
            return "Comment does not exist";
        }
    }

    @Override
    public String deleteComment(int commentID) {
        Optional<Comment> commentOptional = commentRepository.findById(commentID);
        if (commentOptional.isPresent()) {
            commentRepository.deleteById(commentID);
            return "Comment deleted";
        } else {
            return "Comment does not exist";
        }
    }
}
