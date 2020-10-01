package com.cpe.mysql.webblog.services;

import com.cpe.mysql.webblog.entity.Tag;
import com.cpe.mysql.webblog.model.TagModel;
import com.cpe.mysql.webblog.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TagService {

    @Autowired
    private TagRepository tagRepository;

    // add a new tag
    public ResponseEntity<Object> addTag(TagModel model) {
        Tag tag = new Tag();
        if(tagRepository.findByTagName(model.getTagName()).isPresent()) {
            System.out.println("The tag is already present.");
            return ResponseEntity.badRequest().body("The Tag is already Present, Failed to Create new Tag.");
        } else {
            tag.setTagName(model.getTagName());
            tag.setTagColor(model.getTagColor());

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