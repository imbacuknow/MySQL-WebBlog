package com.cpe.mysql.webblog.repository;

import com.cpe.mysql.webblog.entity.Post;
import com.cpe.mysql.webblog.entity.PostTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RepositoryRestResource
public interface PostTagRepository extends JpaRepository<PostTag, Long> {
    List<PostTag> findByPost(Post post);
}
