package com.cpe.mysql.webblog.services;

import com.cpe.mysql.webblog.entity.Role;
import com.cpe.mysql.webblog.model.RoleModel;
import com.cpe.mysql.webblog.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    // add new role
    public ResponseEntity<Object> addRole(RoleModel model) {
        Role role = new Role();
        if(roleRepository.findByRoleOfUser(model.getRoleOfUser()).isPresent()) {
            System.out.println("The role is already present.");
            return ResponseEntity.badRequest().body("The Role is already Present, Failed to Add new Role.");
        } else {
            role.setRoleOfUser(model.getRoleOfUser());
            Role saveRoel = roleRepository.save(role);
            return ResponseEntity.ok("Role is added.");
        }
    }

    // delete a role
    public ResponseEntity<Object> deleteRole(Long id) {
        if(roleRepository.findById(id).isPresent()) {
            roleRepository.deleteById(id);
            if(roleRepository.findById(id).isPresent()) {
                return ResponseEntity.unprocessableEntity().body("Failed to delete(role) the specified record.");
            } else {
                return ResponseEntity.ok().body("Successfully deleted(role) specified record.");
            }
        } else {
            return ResponseEntity.unprocessableEntity().body("No Records Found.");
        }
    }
}
