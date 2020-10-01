package com.cpe.mysql.webblog.controller;

import com.cpe.mysql.webblog.entity.Role;
import com.cpe.mysql.webblog.entity.User;
import com.cpe.mysql.webblog.model.UserModel;
import com.cpe.mysql.webblog.repository.UserRepository;
import com.cpe.mysql.webblog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/webblog/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

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

    @PostMapping("create")
    public ResponseEntity<Object> createUser(@RequestBody UserModel user) {
        return userService.createUser(user);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Object> deleteAUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }
}