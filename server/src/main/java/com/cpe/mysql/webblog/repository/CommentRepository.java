package com.cpe.mysql.webblog.repository;

import com.cpe.mysql.webblog.entity.Comment;
import com.cpe.mysql.webblog.entity.Post;
import com.cpe.mysql.webblog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
@RepositoryRestResource
public interface CommentRepository extends JpaRepository<Comment, Long> {
    Collection<Comment> findByPost(Post post);
}
