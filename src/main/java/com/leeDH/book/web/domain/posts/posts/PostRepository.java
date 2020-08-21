package com.leeDH.book.web.domain.posts.posts;

import com.leeDH.book.web.domain.posts.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<com.leeDH.book.web.domain.posts.posts.Posts, Long> {

    @Query("select p FROM User p order by p.id desc")
    List<com.leeDH.book.web.domain.posts.posts.Posts> findAllDesc();
}
