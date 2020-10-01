package com.cpe.mysql.webblog.controller;

import com.cpe.mysql.webblog.entity.Post;
import com.cpe.mysql.webblog.model.PostModel;
import com.cpe.mysql.webblog.repository.PostRepository;
import com.cpe.mysql.webblog.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

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

    @GetMapping("upcoming")
    public Collection<Post> getUpcomingPage() {
        return postRepository.findByVerified(false);
    }

    @GetMapping("home")
    public Collection<Post> getHomePage() {
        return postRepository.findByVerified(true);
    }

    @PostMapping("create")
    public ResponseEntity<Object> createPost(@RequestBody PostModel post) {
        return postService.createPost(post);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Object> deletePost(@PathVariable Long id) {
        return postService.deletePost(id);
    }
}
