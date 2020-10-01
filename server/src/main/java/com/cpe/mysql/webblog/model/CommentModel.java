package com.cpe.mysql.webblog.model;

import com.cpe.mysql.webblog.entity.Post;
import com.cpe.mysql.webblog.entity.User;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Date;

public class CommentModel {
    @NotNull
    private String msg;
    private Date commentDate;
    private User user;
    private Post post;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Date getCommentDate() {
        Timestamp time = new Timestamp(System.currentTimeMillis());
        java.sql.Date date = new java.sql.Date(time.getTime());
        return date;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
