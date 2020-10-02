package com.cpe.mysql.webblog.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = true)
    private Long id;

    @NotNull
    @Column(name = "title")
    private String title;

    @NotNull
    @Column(name = "story", columnDefinition="VARCHAR(8000)")
    private String story;

    @Column(name = "postDate")
    private Date postDate;

    @Column(name = "verified")
    private boolean verified;

    @ManyToOne
    @JsonIgnore
    private User user; // postContent

    @JoinColumn(name = "post_id")
    @OneToMany(targetEntity = Comment.class, cascade = CascadeType.ALL)
    private List<Comment> comments; // inPost

    @JoinColumn(name = "post_id")
    @OneToMany(targetEntity = PostTag.class, cascade = CascadeType.ALL)
    private List<PostTag> postTags; // ofPost

    public Long getId() {
        return id;
    }

    // Getter
    public String getTitle() {
        return title;
    }

    public String getStory() {
        return story;
    }

    public Date getPostDate() {
        return postDate;
    }

    public boolean getVerified() {
        return verified;
    }

    public User getUser() {
        return user;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public List<PostTag> getPostTags() {
        return postTags;
    }

    // Setter
    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public void setPostTags(List<PostTag> postTags) {
        this.postTags = postTags;
    }
}