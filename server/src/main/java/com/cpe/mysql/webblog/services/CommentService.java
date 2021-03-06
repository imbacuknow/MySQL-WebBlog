package com.cpe.mysql.webblog.services;

import com.cpe.mysql.webblog.entity.Comment;
import com.cpe.mysql.webblog.entity.User;
import com.cpe.mysql.webblog.model.CommentModel;
import com.cpe.mysql.webblog.repository.CommentRepository;
import com.cpe.mysql.webblog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    // add comment to post
    public ResponseEntity<Object> addCommentToPost(CommentModel model) {
        Comment cm = new Comment();

        cm.setMsg(model.getMsg());
        cm.setCommentDate(model.getCommentDate());
        cm.setUser(model.getUser());
        cm.setPost(model.getPost());
        cm.setUserName(model.getUserName());

        Comment saveCM = commentRepository.save(cm);
        return ResponseEntity.ok("Comment is added.");
    }

    // delete a comment
    public ResponseEntity<Object> deleteComment(Long id) {
        if(commentRepository.findById(id).isPresent()) {
            commentRepository.deleteById(id);
            if(commentRepository.findById(id).isPresent()) {
                return ResponseEntity.unprocessableEntity().body("Failed to delete(comment) the specified record.");
            } else {
                return ResponseEntity.ok().body("Successfully deleted(comment) specified record.");
            }
        } else {
            return ResponseEntity.unprocessableEntity().body("No Records Found.");
        }
    }
}
