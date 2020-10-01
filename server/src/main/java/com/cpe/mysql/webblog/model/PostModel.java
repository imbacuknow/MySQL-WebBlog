package com.cpe.mysql.webblog.model;

import com.cpe.mysql.webblog.entity.User;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Date;

public class PostModel {
    @NotNull
    private String title;
    @NotNull
    private String story;
    private Date postDate;
    private boolean verified;
    @NotNull
    private User user;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public Date getPostDate() {
        Timestamp time = new Timestamp(System.currentTimeMillis());
        java.sql.Date date = new java.sql.Date(time.getTime());
        return date;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public boolean getVerified() {
        return false;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
