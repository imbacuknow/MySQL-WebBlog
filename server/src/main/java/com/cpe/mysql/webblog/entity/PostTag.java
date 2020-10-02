package com.cpe.mysql.webblog.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Table(name = "postTag")
public class PostTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = true)
    private Long id;

    @ManyToOne
    @JsonIgnore
    private Tag tag;

    @ManyToOne
    @JsonIgnore
    private Post post;

    // Getter
    public Long getId() {
        return id;
    }

    public Tag getTag() {
        return tag;
    }

    public Post getPost() {
        return post;
    }

    // Setter
    public void setId(Long id) {
        this.id = id;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
