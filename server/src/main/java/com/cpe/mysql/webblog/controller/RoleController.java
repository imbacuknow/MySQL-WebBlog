package com.cpe.mysql.webblog.controller;

import com.cpe.mysql.webblog.entity.Role;
import com.cpe.mysql.webblog.model.RoleModel;
import com.cpe.mysql.webblog.repository.RoleRepository;
import com.cpe.mysql.webblog.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/webblog/role/")
public class RoleController {

    @Autowired
    private RoleRepository roleRepository;

    private RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("details/all")
    public List<Role> getAllRole() {
        return roleRepository.findAll();
    }

    @PostMapping("create")
    public ResponseEntity<Object> addRole(@RequestBody RoleModel role) {
        return roleService.addRole(role);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Object> deleteRole(@PathVariable Long id) {
        return roleService.deleteRole(id);
    }
}
