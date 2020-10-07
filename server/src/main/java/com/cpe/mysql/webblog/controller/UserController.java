package com.cpe.mysql.webblog.controller;

import com.cpe.mysql.webblog.entity.Role;
import com.cpe.mysql.webblog.entity.User;
import com.cpe.mysql.webblog.model.UserModel;
import com.cpe.mysql.webblog.repository.RoleRepository;
import com.cpe.mysql.webblog.repository.UserRepository;
import com.cpe.mysql.webblog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/webblog/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("details/{id}")
    public User getUser(@PathVariable Long id) {
        if(userRepository.findById(id).isPresent())
            return userRepository.findById(id).get();
        else return null;
    }

    @GetMapping("login/{username}/{password}")
    public ResponseEntity<Object> userLogIn(@PathVariable String username, @PathVariable String password) {
        Optional<User> user = userRepository.findByUsernameAndPassword(username, password);
        if(user.isPresent()) {
            return ResponseEntity.ok().body(user.get());
        } else {
            return ResponseEntity.badRequest().body("error: nobody username be " + username);
        }
    }

    @PostMapping("create")
    public ResponseEntity<Object> createUser(@RequestBody UserModel user) {
        return userService.createUser(user);
    }

    @PutMapping("update/{username}/{roleid}")
    public ResponseEntity<Object> updateUser(@PathVariable String username, @PathVariable Long roleid) {
        return userService.updateUserRole(username, roleid);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Object> deleteAUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }
}
