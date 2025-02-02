//PostRepository.java
package com.oop.socialtwitter.repository;

import com.oop.socialtwitter.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    List<Post> findAllByOrderByIdDesc();
}
