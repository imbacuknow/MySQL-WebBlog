package com.cpe.mysql.webblog.controller;

import com.cpe.mysql.webblog.entity.Comment;
import com.cpe.mysql.webblog.entity.Post;
import com.cpe.mysql.webblog.model.CommentModel;
import com.cpe.mysql.webblog.repository.CommentRepository;
import com.cpe.mysql.webblog.repository.PostRepository;
import com.cpe.mysql.webblog.services.CommentService;
import com.cpe.mysql.webblog.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/webblog/comment/")
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("postId/{id}")
    public Collection<Comment> getCommentInPost(@PathVariable Long id) {
        Optional<Post> post = postRepository.findById(id);
        return commentRepository.findByPost(post.get());
    }

    @PostMapping("add_new")
    public ResponseEntity<Object> addNewComment(@RequestBody CommentModel comment) {
        return commentService.addCommentToPost(comment);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Object> deleteComment(@PathVariable Long id) {
        return commentService.deleteComment(id);
    }
}
