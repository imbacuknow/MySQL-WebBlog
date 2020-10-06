package com.cpe.mysql.webblog.repository;

import com.cpe.mysql.webblog.entity.Role;
import com.cpe.mysql.webblog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RepositoryRestResource
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleOfUser(String roleOfUser);

    Role findByUsers(User users);
}
