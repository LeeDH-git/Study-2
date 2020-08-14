package com.leeDH.book.web.domain.posts;

import lombok.Builder;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/* @Entity : 테이블과 링크될 클래스임을 나타내는 어노테이션
 * 기본값으로 클래스의 카멜 표기법이 이름을 _네이밍으로 매칭
 * ex) PostsExample.java -> posts_example table */

@Getter
@NoArgsConstructor // 기본 생성자 자동 추가
@Entity
public class Posts {

    /* @Id : 해당 테이블의 PK
     * @GeneratedValue : PK의 생성규칙을 나타냄
     * GenerationType.IDENTITY > auto_increment
     * @Column : 테이블의 칼럼 (굳이 선언 하지 않아도 모든 클래스의 필드는 모두 컬럼
     * 컬럼 옵션 변경 필요시에만 선언 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;
    private String author;

    @Builder // 빌더 패턴 클래스 생성 , 생성자 선언 시 생성자에 포함된 필드만 빌더에 포함
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
