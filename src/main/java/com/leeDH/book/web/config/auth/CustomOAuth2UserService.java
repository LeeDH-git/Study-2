package com.leeDH.book.web.config.auth;

import com.leeDH.book.web.config.auth.dto.OAuthAttributes;
import com.leeDH.book.web.config.auth.dto.SessionUser;
import com.leeDH.book.web.domain.user.User;
import com.leeDH.book.web.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UserRepository userRepository;
    private final HttpSession httpSession;

    @Override
    public DefaultOAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2UserService delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        /* 현재 로그인 진행 중인 서비스를 구분하는 코드
         * 지금은 구글만 사용하는 불필요한 갑이지만, 이후 네이버 로그이 연동시에 네이버 로그인인지, 구글 로그인인지 구분하기 위해 사용*/
        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        /* OAuth2 로그인 진행 시 키가 되는 필드값을 이야기 한다, Primary Key와 같은 의미
         * 구글의 경우 기본적으로 코드를 지원하지만, 네이버 카카오 등은 기본 지원하지 않는다 (구글의 기본코드 = "sub")
         * 이후 네이버 로그인과 구글 로그인 동시 지원시 사용*/
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails()
                .getUserInfoEndpoint().getUserNameAttributeName();

        /* OAuth2UserService를 통해 가져온 OAuth2User의 attribute를 담을 클래스
         * 이후 네이버 등 다른 소셜 로그인도 이 클래스 사용*/
        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());

        User user = saveOrUpdate(attributes);

        /* 세션에 사용자 정보를 저장하기 위한 DTO 클래스
         *  new SessionUser(user) > 왜 User 클래스를 사용하지 않고 새로 만들어 쓰는지?
         * - User 클래스 사용시 Failed to convert from type [java.lang.Object] to type [byte[] for value '~' 에러 발생
         * -> 이는 세션에 저장하기 위해 User 클래스를 세션에 저장하려고 하니 User 클래스에 직렬화를 구현하지 않았다는 의미
         *    오류를 위해서 User 클래스에 직렬화 코드를 넣는것도 안됨!
         *    User 클래스가 엔티티이기 때문에 언제 다른 엔티티와 관계가 형성될지 모르기 때문에
         *    자식 엔티티를 갖고 있다면 대상의 자식들까지 직렬화의 대상이 되버려 성능 이슈, 부가 효과 등이 발생 할 수 있기 때문이다.
         *    그래서 직렬화 기능을 가진 세션 DTO를 하나 추가로 만드는게 유지보수 측면에서 더 도움 됨 */
        httpSession.setAttribute("user", new SessionUser(user));

        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey())),
                attributes.getAttributes(),
                attributes.getNameAttributeKey());
    }

    private User saveOrUpdate(OAuthAttributes attributes) {
        User user = userRepository.findByEmail(attributes.getEmail())
                .map(entity -> entity.update(attributes.getName(), attributes.getPicture()))
                .orElse(attributes.toEntity());

        return userRepository.save(user);
    }
}

