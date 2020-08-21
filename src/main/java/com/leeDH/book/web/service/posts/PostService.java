package com.leeDH.book.web.service.posts;

import com.leeDH.book.web.domain.posts.PostRepository;
import com.leeDH.book.web.domain.posts.Posts;
import com.leeDH.book.web.dto.PostsListsResponseDTO;
import com.leeDH.book.web.dto.PostsResponseDTO;
import com.leeDH.book.web.dto.PostsSaveRequestDTO;
import com.leeDH.book.web.dto.PostsUpdateRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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
        Posts posts = postRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("해당 게시물이 없습니다. id=" + id));
        posts.update(requestDTO.getTitle(), requestDTO.getContent());

        return id;
    }

    @Transactional
    public PostsResponseDTO findById(Long id) {
        Posts entity = postRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("해당 게시물이 없습니다. id=" + id));

        return new PostsResponseDTO(entity);
    }

    @Transactional(readOnly = true)
    public List<PostsListsResponseDTO> findAllDesc() {

        return postRepository.findAllDesc().stream()
                .map(PostsListsResponseDTO::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id) {
        Posts posts = postRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        postRepository.delete(posts);

    }
}
