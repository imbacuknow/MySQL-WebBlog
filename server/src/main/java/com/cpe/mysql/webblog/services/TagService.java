package com.cpe.mysql.webblog.services;

import com.cpe.mysql.webblog.entity.Post;
import com.cpe.mysql.webblog.entity.PostTag;
import com.cpe.mysql.webblog.entity.Tag;
import com.cpe.mysql.webblog.model.TagModel;
import com.cpe.mysql.webblog.repository.PostRepository;
import com.cpe.mysql.webblog.repository.PostTagRepository;
import com.cpe.mysql.webblog.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TagService {

    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private PostTagRepository postTagRepository;

    // retrieve tag by post_id
    public List<PostTag> retrieveTags(Long id) {
        Optional<Post> post = postRepository.findById(id);
        List<PostTag> postTag = postTagRepository.findByPost(post.get());
        return postTag;
    }

    // add a new tag
    public ResponseEntity<Object> addTag(TagModel model) {
        Tag tag = new Tag();
        if(tagRepository.findByText(model.getText()).isPresent()) {
            System.out.println("The tag is already present.");
            return ResponseEntity.badRequest().body("The Tag is already Present, Failed to Create new Tag.");
        } else {
            tag.setText(model.getText());
            tag.setColor(model.getColor());

            Tag saveTag = tagRepository.save(tag);
            return ResponseEntity.ok("Tag is added.");
        }
    }

    // delete a tag
    public ResponseEntity<Object> deleteTag(Long id) {
        if(tagRepository.findById(id).isPresent()) {
            tagRepository.deleteById(id);
            if(tagRepository.findById(id).isPresent()) {
                return ResponseEntity.unprocessableEntity().body("Failed to delete(tag) the specified record.");
            } else {
                return ResponseEntity.ok().body("Successfully deleted(tag) specified record.");
            }
        } else {
            return ResponseEntity.unprocessableEntity().body("No Records Found.");
        }
    }
}