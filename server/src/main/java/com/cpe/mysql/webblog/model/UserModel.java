package com.cpe.mysql.webblog.model;

import com.cpe.mysql.webblog.entity.Role;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Date;

public class UserModel {
    @NotNull
    private String username;
    @NotNull
    private String password;
    private String name;
    private String email;
    private Date registerDate;
    private Role role;
    private String roleName;

    public String getRoleName() {
        return getRole().getRoleOfUser();
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getRegisterDate() {
        Timestamp time = new Timestamp(System.currentTimeMillis());
        java.sql.Date date = new java.sql.Date(time.getTime());
        return date;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
