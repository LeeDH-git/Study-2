package com.leeDH.book.web.service.posts;

import com.leeDH.book.web.domain.posts.PostRepository;
import com.leeDH.book.web.domain.posts.Posts;
import com.leeDH.book.web.dto.PostsResponseDTO;
import com.leeDH.book.web.dto.PostsSaveRequestDTO;
import com.leeDH.book.web.dto.PostsUpdateRequestDTO;
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

    @Transactional
    public Long update(Long id, PostsUpdateRequestDTO requestDTO) {
        Posts posts = postRepository.findById(id).orElseThrow(() -> new
                IllegalArgumentException("해당 게시물이 없습니다. id=" + id));
        posts.update(requestDTO.getTitle(), requestDTO.getContent());

        return id;
    }

    @Transactional
    public PostsResponseDTO findById(Long id) {
        Posts entity = postRepository.findById(id).orElseThrow(() -> new
                IllegalArgumentException("해당 게시물이 없습니다. id=" + id));

        return new PostsResponseDTO(entity);
    }

}
