package com.ch08.security;

import com.ch08.entity.User;
import com.ch08.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MyUserDetailService implements UserDetailsService {

    // MyUserDetailService는 Bean 등록 해야함
    // 사용자가 ID/PW 입력 ->

    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 사용자가 입력한 아이디로 사용자 조회, 비밀번호에 대한 검증은 이전 컴포넌트인 AuthenticationProvider 수행
        Optional<User> optUser = userRepository.findById(username);
        
        if(optUser.isPresent()) {
            // 씨큐리티 사용자 인증객체 생성 후 리턴
            MyUserDetails myUserDetails = MyUserDetails.builder()
                                                     .user(optUser.get())
                                                     .build();

            return myUserDetails;
        }
        
        // 사용자가 입력한 아이디가 없을 경우
        return null;
    }
        // 비밀번호 검색은 security 가 다 알아서 해줌
        // 아이디만 조회 하기 떄문에 비밀번호가 틀려도 상관없다

}
