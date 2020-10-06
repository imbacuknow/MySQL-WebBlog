package com.cpe.mysql.webblog.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "tag")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = true)
    private Long id;

    @NotNull
    @Column(name = "name_tag")
    private String text;

    @NotNull
    @Column(name = "tag_color")
    private String color;

    @JoinColumn(name = "tag_id")
    @OneToMany(targetEntity = PostTag.class)
    private List<PostTag> postTags; // relateTag

    // Getter
    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getColor() {
        return color;
    }

    public List<PostTag> getPostTags() {
        return postTags;
    }

    // Setter
    public void setId(Long id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setPostTags(List<PostTag> postTags) {
        this.postTags = postTags;
    }

    // Constructor
    public Tag() {}

    public Tag(@NotNull String text,
               @NotNull String color) {
        this.text = text;
        this.color = color;
    }
}
