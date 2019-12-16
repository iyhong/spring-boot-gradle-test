package com.hong.springboot.web.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @After
    public void cleanUp () {
        postsRepository.deleteAll();
    }

    @Test
    public void BaseTimeEntity_등록 () {
        // given
        LocalDateTime now = LocalDateTime.of(2019,12,16,0,0,0);
        postsRepository.save(Posts.builder()
                                    .title("title")
                                    .content("content")
                                    .author("author")
                                    .build()
        );

        // when
        Posts posts = postsRepository.findAll().get(0);
        System.out.println(">>>>> createDate="+posts.getCreatedDate() + ", modifiedDate="+posts.getModifyiedDate());

        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getModifyiedDate()).isAfter(now);

        // then
    }

    @Test
    public void 게시글_저장_불러오기 () {

        // given
        String title = "제목1";
        String content = "내용1";
        postsRepository.save(Posts.builder()
                                    .title(title)
                                    .content(content)
                                    .author("홍인용")
                                    .build()
        );

        // when
        List<Posts> posts = postsRepository.findAll();

        // then
        Posts post = posts.get(0);
        assertThat(post.getTitle()).isEqualTo(title);
        assertThat(post.getContent()).isEqualTo(content);
    }

}