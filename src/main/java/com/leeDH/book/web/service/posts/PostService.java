package com.leeDH.book.web.service.posts;

import com.leeDH.book.web.domain.posts.PostRepository;
import com.leeDH.book.web.dto.PostsSaveRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;

    @Transactional
    public Long save(PostsSaveRequestDTO requestDTO) {
        return postRepository.save(requestDTO.toEntity()).getId();
    }
}
