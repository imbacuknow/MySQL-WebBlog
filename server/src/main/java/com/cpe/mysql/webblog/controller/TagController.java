package com.cpe.mysql.webblog.controller;

import com.cpe.mysql.webblog.entity.Tag;
import com.cpe.mysql.webblog.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/webblog/tag/")
public class TagController {

    @Autowired
    private TagRepository tagRepository;

//    @GetMapping("tagName={tagname}")
//    public Tag getTagName(@PathVariable("tagname") String tagname) {
//        return tagRepository.findByTagName(tagname);
//    }
}
