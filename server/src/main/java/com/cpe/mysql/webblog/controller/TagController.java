package com.cpe.mysql.webblog.controller;

import com.cpe.mysql.webblog.entity.Tag;
import com.cpe.mysql.webblog.model.TagModel;
import com.cpe.mysql.webblog.repository.TagRepository;
import com.cpe.mysql.webblog.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/webblog/tag/")
public class TagController {

    @Autowired
    private TagRepository tagRepository;

    private TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping("showall")
    public Collection<Tag> getAllTag() {
        return tagRepository.findAll();
    }

    @PostMapping("add")
    public ResponseEntity<Object> addTag(@RequestBody TagModel tag) {
        return tagService.addTag(tag);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Object> deleteTag(@PathVariable Long id) {
        return tagService.deleteTag(id);
    }
}
