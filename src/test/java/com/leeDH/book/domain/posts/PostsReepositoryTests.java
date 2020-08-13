package com.leeDH.book.domain.posts;

import com.leeDH.book.web.domain.posts.PostRepository;
import com.leeDH.book.web.domain.posts.Posts;
import com.leeDH.book.web.dto.HelloResponseDTO;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest // 자동 으로 H2DataBase 실행
public class PostsReepositoryTests {

    @Autowired
    PostRepository postRepository;

    @After // Junit에서 단위 테스트가 끝날 때마다 수행되는 메서드를 지정, 보통 배포 전 전체 테스트를 수행할 때 테스트간 데이터 침범을 막기 위해 사용
    public void cleanup() {
        postRepository.deleteAll();
    }

    @Test
    public void content_load() {

        //given
        String title = "테스트 게시글";
        String content = "테스트 본문";

                postRepository.save(Posts.builder()

                .title(title)
                .content(content)
                .author("leeDH@gmail.com")
                .build());
        //when
        List<Posts> postsList = postRepository.findAll();

        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);

    }

}
