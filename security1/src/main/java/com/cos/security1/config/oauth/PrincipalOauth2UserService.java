package com.cos.security1.config.oauth;

import com.cos.security1.config.auth.PrincipalDetails;
import com.cos.security1.model.User;
import com.cos.security1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepository userRepository;

    //구글로부터 받은 uesrRequest 데이터에 대한 후처리되는 함수
    //함수 종료시 @AuthenticationPrincipal 만들어진다.
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        System.out.println("getClientRegistration:" + userRequest.getClientRegistration()); // registartionId로 어떤 Oauth로 로그인 했는지 알수 있음.
        System.out.println("getAccessToken:" + userRequest.getAccessToken().getTokenValue());

        System.out.println("getAccessToken:" + userRequest.getAccessToken().getTokenValue());

        OAuth2User oAuth2User = super.loadUser(userRequest);
        //구글 로그인 버튼 클릭  -> 구글 로그인창 -> 로그인을 완료 -> code를 리턴(Oauth-client라이브러리) -> AccessToken요청
        //userRequest정보 -> loadUser 함수 -> 회원프로필 -> 구글로부터 회원프로필 받아준다.
        System.out.println("getAttributes:" + oAuth2User.getAttributes());

        String provider = userRequest.getClientRegistration().getClientId();
        String providerId = oAuth2User.getAttribute("sub");
        String username = provider + "_" + providerId; // google_109742~~~
        String password = bCryptPasswordEncoder.encode("겟인데여");
        String email = oAuth2User.getAttribute("email");
        String role = "ROLE_USER";

        User userEntity= userRepository.findByUsername(username);

        if(userEntity == null){
            userEntity = User.builder()
                    .username(username)
                    .password(password)
                    .email(email)
                    .role(role)
                    .provider(provider)
                    .providerId(providerId)
                    .build();
            userRepository.save(userEntity);
        }

        //회원가입을 강제로 진행해볼 예정
        return new PrincipalDetails(userEntity,oAuth2User.getAttributes());
    }
}
