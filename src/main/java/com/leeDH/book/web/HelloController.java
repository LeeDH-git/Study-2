package com.leeDH.book.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // Controller를 JSON을 반환하는 컨트롤러로 만들어 줌 , @RequestBody를 각 메서드마다 선언했던것을 한번에
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
}
