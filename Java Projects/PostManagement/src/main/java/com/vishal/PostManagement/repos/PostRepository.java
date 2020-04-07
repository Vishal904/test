package com.vishal.PostManagement.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.vishal.PostManagement.entities.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

	
	List<Post> findByUserid(Long userid);
	
	@Transactional
	@Modifying
    @Query("UPDATE Post p SET p.posttitle = :title, p.description = :description WHERE p.id = :postid")
    int updatePost(@Param("postid") Long postid, @Param("title") String title, @Param("description") String description);
}
