//PostServiceImpl.java
package com.oop.socialtwitter.service.impl;

import com.oop.socialtwitter.controller.dto.*;
import com.oop.socialtwitter.model.Comment;
import com.oop.socialtwitter.model.Post;
import com.oop.socialtwitter.model.User;
import com.oop.socialtwitter.repository.CommentRepository;
import com.oop.socialtwitter.repository.PostRepository;
import com.oop.socialtwitter.repository.UserRepository;
import com.oop.socialtwitter.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommentRepository commentRepository; // Autowire CommentRepository


    @Override
    public String createPost(PostRequest postRequest) {
        Optional<User> user = userRepository.findById(postRequest.getUserID());
        if (user.isPresent()) {
            Post post = new Post();
            post.setPostBody(postRequest.getPostBody());
            post.setUser(user.get());
            Date currentDate = new Date(System.currentTimeMillis());
            post.setDate(currentDate);
            postRepository.save(post);
            return "Post created successfully";
        } else {
            return "User does not exist";
        }
    }

    //getPost with comments
    @Override
    public PostResponse getPost(int postID) {
        Optional<Post> postOptional = postRepository.findById(postID);
        if (postOptional.isPresent()) {
            Post post = postOptional.get();
            PostResponse postResponse = new PostResponse();
            postResponse.setPostID(post.getId());
            postResponse.setPostBody(post.getPostBody());
            postResponse.setDate(post.getDate());

            List<Comment> comments = commentRepository.findByPostId(postID); // Fetch comments by postID
            List<CommentResponse> commentResponses = new ArrayList<>();
            for (Comment comment : comments) {
                CommentResponse commentResponse = new CommentResponse();
                commentResponse.setCommentID(comment.getId());
                commentResponse.setCommentBody(comment.getCommentBody());
                CommentCreator creator = new CommentCreator();
                creator.setUserID(comment.getUser().getId());
                creator.setName(comment.getUser().getName());
                commentResponse.setCommentCreator(creator);
                commentResponses.add(commentResponse);
            }
            postResponse.setComments(commentResponses);

            return postResponse;
        } else {
            return null;
        }
    }

    @Override
    public String editPost(PostEdit postEdit) {
        Optional<Post> postOptional = postRepository.findById(postEdit.getPostID());
        if (postOptional.isPresent()) {
            Post post = postOptional.get();
            post.setPostBody(postEdit.getPostBody());
            postRepository.save(post);
            return "Post edited successfully";
        } else {
            return "Post does not exist";
        }
    }

    @Override
    public String deletePost(int postID) {
        Optional<Post> postOptional = postRepository.findById(postID);
        if (postOptional.isPresent()) {
            // Delete associated comments by post ID
            List<Comment> comments = commentRepository.findByPostId(postID);
            commentRepository.deleteAll(comments);

            // Delete the post itself
            postRepository.deleteById(postID);
            return "Post deleted";
        } else {
            return "Post does not exist";
        }
    }

    @Override
    public List<PostResponse> getAllPostsInReverseChronologicalOrder() {
        List<Post> allPosts = postRepository.findAllByOrderByIdDesc(); // Assuming findAllByOrderByIdDesc() method in PostRepository
        return allPosts.stream()
                .map(this::convertToPostResponse)
                .collect(Collectors.toList());
    }

    private PostResponse convertToPostResponse(Post post) {
        PostResponse postResponse = new PostResponse();
        postResponse.setPostID(post.getId());
        postResponse.setPostBody(post.getPostBody());
        postResponse.setDate(post.getDate());

        List<Comment> comments = commentRepository.findByPostId(post.getId());
        List<CommentResponse> commentResponses = new ArrayList<>();
        for (Comment comment : comments) {
            CommentResponse commentResponse = new CommentResponse();
            commentResponse.setCommentID(comment.getId());
            commentResponse.setCommentBody(comment.getCommentBody());
            CommentCreator creator = new CommentCreator();
            creator.setUserID(comment.getUser().getId());
            creator.setName(comment.getUser().getName());
            commentResponse.setCommentCreator(creator);
            commentResponses.add(commentResponse);
        }
        postResponse.setComments(commentResponses);
        return postResponse;
    }
}
