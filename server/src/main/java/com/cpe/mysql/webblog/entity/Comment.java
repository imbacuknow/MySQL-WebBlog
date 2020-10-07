package com.cpe.mysql.webblog.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = true)
    private Long id;

    @NotNull
    @Column(name = "massage")
    private String msg;

    @NotNull
    @Column(name = "comment_data")
    private Date commentDate;

    @ManyToOne
    @JsonIgnore
    private User user; // commented

    @ManyToOne
    @JsonIgnore
    private Post post; // ofComment

    @Transient
    private String userName;

    public String getUserName() {
        return getUser().getUsername();
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    // Getter
    public Long getId() {
        return id;
    }

    public String getMsg() {
        return msg;
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public User getUser() {
        return user;
    }

    public Post getPost() {
        return post;
    }

    // Setter
    public void setId(Long id) {
        this.id = id;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}