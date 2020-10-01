package com.cpe.mysql.webblog.repository;

import com.cpe.mysql.webblog.entity.PostTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource
public interface PostTagRepository extends JpaRepository<PostTag, Long> {
}
