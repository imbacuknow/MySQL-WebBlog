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
    private String tagName;

    @NotNull
    @Column(name = "tag_color")
    private String tagColor;

//    @JoinColumn(name = "tag_id")
//    @OneToMany(targetEntity = PostTag.class, mappedBy = "postTag")
//    private List<PostTag> postTags; // relateTag

    // Getter
    public Long getId() {
        return id;
    }

    public String getTagName() {
        return tagName;
    }

    public String getTagColor() {
        return tagColor;
    }

//    public List<PostTag> getPostTags() {
//        return postTags;
//    }

    // Setter
    public void setId(Long id) {
        this.id = id;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public void setTagColor(String tagColor) {
        this.tagColor = tagColor;
    }

//    public void setPostTags(List<PostTag> postTags) {
//        this.postTags = postTags;
//    }

    // Constructor
    public Tag() {}

    public Tag(@NotNull String tagName,
               @NotNull String tagColor) {
        this.tagName = tagName;
        this.tagColor = tagColor;
    }
}
