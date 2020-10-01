package com.cpe.mysql.webblog.repository;

import com.cpe.mysql.webblog.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RepositoryRestResource
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByVerified(boolean b);
}
