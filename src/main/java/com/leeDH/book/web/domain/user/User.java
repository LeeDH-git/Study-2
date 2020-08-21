package com.leeDH.book.web.domain.user;

import com.leeDH.book.web.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/* @Entity : 테이블과 링크될 클래스임을 나타내는 어노테이션
 * 기본값으로 클래스의 카멜 표기법이 이름을 _네이밍으로 매칭
 * ex) PostsExample.java -> posts_example table */
@Getter
@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity {

    /* @Id : 해당 테이블의 PK
     * @GeneratedValue : PK의 생성규칙을 나타냄
     * GenerationType.IDENTITY > auto_increment
     * @Column : 테이블의 칼럼 (굳이 선언 하지 않아도 모든 클래스의 필드는 모두 컬럼
     * 컬럼 옵션 변경 필요시에만 선언 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column
    private String picture;

    /*
     * @Enumerated(EnumType.STRING)
     * - JPA로 데이터베이스로 저장할 때 Enum 값을 어떤 형태로 저장할지를 결정한다.
     * - 숫자로 저장되면 데이터베이스로 확인할 때 그 값이 무슨 코드를 의미하는지 알 수가 없다.
     * - 그래서 문자열(EnumType.STRING)로 저장될 수 있도록 선언한다.
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Builder
    public User(String name, String email, String picture, Role role) {
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.role = role;
    }

    public User update(String name, String picture) {
        this.name = name;
        this.picture = picture;

        return this;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }
}
