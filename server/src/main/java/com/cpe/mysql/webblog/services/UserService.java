package com.cpe.mysql.webblog.services;

import com.cpe.mysql.webblog.entity.Post;
import com.cpe.mysql.webblog.entity.Role;
import com.cpe.mysql.webblog.entity.User;
import com.cpe.mysql.webblog.model.UserModel;
import com.cpe.mysql.webblog.repository.RoleRepository;
import com.cpe.mysql.webblog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

//    // create a new user
    public ResponseEntity<Object> createUser(UserModel model) {
        User user = new User();

        if(userRepository.findByEmail(model.getEmail()).isPresent()) {
            System.out.println("The email is already present.");
            return ResponseEntity.badRequest().body("The Email is already Present, Failed to Create new User.");
        } else if(userRepository.findByUsername(model.getUsername()).isPresent()){
            System.out.println("The username is already present.");
            return ResponseEntity.badRequest().body("The Username is already Present, Failed to Create new User.");
        } else {
            user.setUsername(model.getUsername());
            user.setPassword(model.getPassword());
            user.setName(model.getName());
            user.setEmail(model.getEmail());
            user.setRegisterDate(model.getRegisterDate());
            user.setRole(model.getRole());

            User saveUser = userRepository.save(user);
            return ResponseEntity.ok("User is created.");
        }
    }

    // delete a user
    public ResponseEntity<Object> deleteUser(Long id) {
        if(userRepository.findById(id).isPresent()) {
            userRepository.deleteById(id);
            if(userRepository.findById(id).isPresent()) {
                return ResponseEntity.unprocessableEntity().body("Failed to delete(user) the specified record.");
            } else {
                return ResponseEntity.ok().body("Successfully deleted(user) specified record.");
            }
        } else {
            return ResponseEntity.unprocessableEntity().body("No Records Found.");
        }
    }
}
