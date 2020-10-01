package com.cpe.mysql.webblog.services;

import com.cpe.mysql.webblog.entity.Post;
import com.cpe.mysql.webblog.model.PostModel;
import com.cpe.mysql.webblog.repository.PostRepository;
import com.cpe.mysql.webblog.repository.TagRepository;
import com.cpe.mysql.webblog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    // create a new post
    public ResponseEntity<Object> createPost(PostModel model) {
        Post np = new Post();
        np.setTitle(model.getTitle());
        np.setStory(model.getStory());
        np.setPostDate(model.getPostDate());
        np.setVerified(model.getVerified());
        np.setUser(model.getUser());

        Post saveNP = postRepository.save(np);
        return ResponseEntity.ok("Post Create Successfully.");
    }

    // delete pot
    public ResponseEntity<Object> deletePost(Long id) {
        if(postRepository.findById(id).isPresent()) {
            postRepository.deleteById(id);
            if(postRepository.findById(id).isPresent()) {
                return ResponseEntity.unprocessableEntity().body("Failed to delete(post) the specified record.");
            } else {
                return ResponseEntity.ok().body("Successfully deleted(post) specified record.");
            }
        } else {
            return ResponseEntity.unprocessableEntity().body("No Records Found.");
        }
    }
}
