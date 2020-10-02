package com.cpe.mysql.webblog.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = true)
    private Long id;

    // มี 3 ระดับ
    @NotNull                        // 1. Newbie พึ่งสมัครใหม่ หรือ ยังมีการโพสน้อย
    @Column(name = "role_of_user")  // 2. Experienced มีการโพสเยอะ หรือ สมัครนานแล้ว
    private String roleOfUser;      // 3. Admin

    @JoinColumn(name = "role_id")
    @OneToMany(targetEntity = User.class, cascade = CascadeType.ALL)
    private List<User> users; // userRole

    // Getter
    public Long getId() {
        return id;
    }

    public String getRoleOfUser() {
        return roleOfUser;
    }

    public List<User> getUsers() {
        return users;
    }

    // Setter
    public void setId(Long id) {
        this.id = id;
    }

    public void setRoleOfUser(String roleOfUser) {
        this.roleOfUser = roleOfUser;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    // Constructor
    public Role() {}

    public Role(@NotNull String roleOfUser) {
        this.roleOfUser = roleOfUser;
    }
}