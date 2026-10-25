package com.github.gubbib.backend.security.service;

import com.github.gubbib.backend.domain.user.service.UserService;
import com.github.gubbib.backend.security.details.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService userService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    /*
        CustomUserDetails에서 만든 getUsername 을 호출하는 놈
        저 String username 에는 member.getEmail() 값이 들어온다
    */
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.debug("[+] CustomUserDetailsService - loadUserByUsername :: 회원 조회 요청 - email={}", username);

        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("EMAIL", username);

        HashMap<String, Object> user = userService.selectActiveAndPendingUser(parameters);

        if(user == null || user.isEmpty()){
            logger.debug("[+] CustomUserDetailsService - loadUserByUsername :: 존재하지 않거나 비활성화된 회원 - email={}", username);
            throw new UsernameNotFoundException("존재하지 않거나 비활성화된 회원입니다.");
        }

        return new CustomUserDetails(user);

    }
}
