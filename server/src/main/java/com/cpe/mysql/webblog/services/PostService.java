package com.cpe.mysql.webblog.services;

import com.cpe.mysql.webblog.entity.Post;
import com.cpe.mysql.webblog.entity.PostTag;
import com.cpe.mysql.webblog.entity.Tag;
import com.cpe.mysql.webblog.model.PostModel;
import com.cpe.mysql.webblog.repository.PostRepository;
import com.cpe.mysql.webblog.repository.PostTagRepository;
import com.cpe.mysql.webblog.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private PostTagRepository postTagRepository;

    // create a new post
    public ResponseEntity<Object> createPost(PostModel model) {
        Post np = new Post();
        np.setTitle(model.getTitle());
        np.setStory(model.getStory());
        np.setPostDate(model.getPostDate());
        np.setVerified(model.getVerified());
        np.setUser(model.getUser());

        Post saveNP = postRepository.save(np);

        // map posttag
        for(int i = 0; i < model.getTag().length; i++) {
            addPostTag(model.getTag()[i], np);
        }

        return ResponseEntity.ok("Post Create Successfully.");
    }

    // add post along with tag
    public ResponseEntity<Object> addPostTag(String tag, Post np){
        PostTag pt = new PostTag();
        Optional<Tag> tg = tagRepository.findByTagName(tag);
        pt.setTag(tg.get());
        pt.setPost(np);
        postTagRepository.save(pt);
        return ResponseEntity.ok("PostTag " + tag);
    }

    // This post is verified
    public ResponseEntity<Object> updateVerified(Long id) {
        if(postRepository.findById(id).isPresent()) {
            Optional<Post> update_post = postRepository.findById(id).map(post -> {
                post.setVerified(true);
                return postRepository.save(post);
            });
            return ResponseEntity.ok("Post confirm for read.");
        } else return ResponseEntity.unprocessableEntity().body("No Records Found.");
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
