package com.leeDH.book.dto;

import com.leeDH.book.web.dto.HelloResponseDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class HelloResponseDTOTests {


    /*
     * assertThat : assertj라는 테스트 검증 라이브러리의 메서드
     * 검증하고 싶은 대상을 메서드 인자로 받음
     * 메서드 체이닝이 지원되어 isEqualTo와 같이 메서드를 이어서 사용할 수 있다
     *
     * isEqualTo : assertj의 동등 비교 메서드
     * assertThat에 있는 값과 isEqualTo의 값을 비교해서 같을때만 성공 */

    @Test
    public void lombokTests() {

        //given
        String name = "test";
        int amount = 1000;

        //when

        HelloResponseDTO dto = new HelloResponseDTO(name, amount);

        //then
        assertThat(dto.getName()).isEqualTo(name);
        assertThat(dto.getAmount()).isEqualTo(amount);
    }

}
