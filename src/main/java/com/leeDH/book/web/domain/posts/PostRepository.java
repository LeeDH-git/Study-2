package com.leeDH.book.web.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Posts, Long> {

    @Query("SELECT P FROM User P ORDER BY P.id DESC")
    List<Posts> findAllDesc();
}
