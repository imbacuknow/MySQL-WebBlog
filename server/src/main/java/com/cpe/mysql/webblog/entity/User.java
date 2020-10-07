package com.cpe.mysql.webblog.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = true)
    private Long id;

    @NotNull
    @Column(name = "username", unique = true)
    private String username;

    @NotNull
    @Column(name = "password")
    private String password;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Email(message = "Email should be valid")
    @Column(name = "email")
    private String email;

    @Column(name = "register_date")
    private Date registerDate;

    @ManyToOne(targetEntity = Role.class)
    @JsonIgnore
    private Role role; // roleOf

    @JoinColumn(name = "user_id")
    @OneToMany(targetEntity = Post.class, cascade = CascadeType.ALL)
    private List<Post> posts; // postBy

    @JoinColumn(name = "user_id")
    @OneToMany(targetEntity = Comment.class)
    private List<Comment> comments; // ofUser

    @Transient
    private String roleName;

    public String getRoleName() {
        return getRole().getRoleOfUser();
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    // Getter
    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public Role getRole() {
        return role;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public List<Comment> getComments() {
        return comments;
    }

    // Setter
    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    // Constructor
    public User() {}

    public User(@NotNull String username,
                @NotNull String password,
                @NotNull String name,
                @NotNull String email,
                @NotNull Date registerDate,
                @NotNull Role role,
                @NotNull String roleName) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.registerDate = registerDate;
        this.role = role;
        this.roleName = roleName;
    }
}