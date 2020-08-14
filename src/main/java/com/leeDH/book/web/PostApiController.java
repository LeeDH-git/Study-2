package com.leeDH.book.web;

import com.leeDH.book.web.dto.PostsSaveRequestDTO;
import com.leeDH.book.web.service.posts.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class PostApiController {
    private final PostService postService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDTO requestDTO) {
        return postService.save(requestDTO);
    }
}
