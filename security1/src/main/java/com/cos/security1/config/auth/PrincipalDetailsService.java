package com.cos.security1.config.auth;

import com.cos.security1.model.User;
import com.cos.security1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

// 시큐리티 설정에서 loginProcessingUrl("/login")
// /login 요청이 오면 자동으로 UserDetailsService 타입으로 IoC 되어 있는 loadUserByUsername 함수가 실행
@Service
public class PrincipalDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    // 시큐리티 session(내부 Authentication(내부 UserDetails))
    // loadUserBy가 로그인을 시켜줌
    // 함수 종료시 @AuthenticationPrincipal 만들어진다.
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //username 중요!  username default
        //바꾸고 싶다면 username을 config에서 parameter로 바꿔야함
        User userEntity = userRepository.findByUsername(username);

        if(userEntity != null){
            return new PrincipalDetails(userEntity);
        }

        return null;
    }
}
