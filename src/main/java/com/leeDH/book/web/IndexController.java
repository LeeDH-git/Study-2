package com.leeDH.book.web;

import com.leeDH.book.web.config.auth.LoginUser;
import com.leeDH.book.web.config.auth.dto.SessionUser;
import com.leeDH.book.web.dto.PostsResponseDTO;
import com.leeDH.book.web.service.posts.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.h2.engine.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostService postService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {
        /* @LoginUser SessionUser user : 기존의 SessionUser user = (SessionUser) httpSession.getAttribute("user"); 을 개선
         * 이제 @LoginUser 만 사용하면 세션 정보를 가져올 수 있음 */
        model.addAttribute("posts", postService.findAllDesc());

        /* CustomOAuth2UserService에서 로그인 성공 시 세션에 SessionUser 저장 하도록 구성
         * 즉, 로그인 성공시 httpSession.getAttribute에서 값을 가져올 수 있다.
        SessionUser user = (SessionUser) httpSession.getAttribute("user");*/

        /* 세션에 저장된 값이 있을때만 model에 userName으로 등록
         *  세션에 저장된 값이 없다면 model에 아무런 값이 없는 상태이니 로그인 버튼이 보이게 된다. */
        if (user != null) {
            model.addAttribute("userName", user.getName());
        }
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {

        PostsResponseDTO dto = postService.findById(id);
        model.addAttribute("posts", dto);
        return "posts-update";
    }


}
