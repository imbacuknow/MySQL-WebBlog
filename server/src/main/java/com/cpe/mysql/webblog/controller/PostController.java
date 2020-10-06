package com.cpe.mysql.webblog.controller;

import com.cpe.mysql.webblog.entity.Post;
import com.cpe.mysql.webblog.model.PostModel;
import com.cpe.mysql.webblog.repository.PostRepository;
import com.cpe.mysql.webblog.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/webblog/post/")
public class PostController {

    @Autowired
    private PostRepository postRepository;

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping(value = "upcoming", produces = "application/json;charset=UTF-8")
    public Collection<Post> getUpcomingPage() {
        return postRepository.findByVerified(false);
    }

    @GetMapping("home")
    public Collection<Post> getHomePage() {
        return postRepository.findByVerified(true);
    }

    @GetMapping("content/{id}")
    public Optional<Post> getContentId(@PathVariable Long id) {
        return postRepository.findById(id);
    }

    @PostMapping(value = "create", produces = "application/json;charset=UTF-8")
    public ResponseEntity<Object> createPost(@RequestBody PostModel post) {
        return postService.createPost(post);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<Object> updatePost(@PathVariable Long id) {
        return postService.updateVerified(id);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Object> deletePost(@PathVariable Long id) {
        return postService.deletePost(id);
    }
}
