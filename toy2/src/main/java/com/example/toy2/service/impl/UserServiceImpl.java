package com.example.toy2.service.impl;

import com.example.toy2.domain.Article;
import com.example.toy2.domain.User;
import com.example.toy2.domain.enums.Role;
import com.example.toy2.dto.TokenDto;
import com.example.toy2.dto.exception.user.UserNotFoundException;
import com.example.toy2.dto.user.ChangeNicknameRequestDto;
import com.example.toy2.dto.user.LoginRequestDto;
import com.example.toy2.dto.user.SignUpRequestDto;
import com.example.toy2.jwt.TokenProvider;
import com.example.toy2.repository.UserRepository;
import com.example.toy2.service.UserService;
import com.example.toy2.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final TokenProvider tokenProvider;

    @Override
    public void signup(SignUpRequestDto dto){
        // User 클래스 틀에 맞춰 값 대입
        User user = User.builder()
                .username(dto.getId())
                .pw(passwordEncoder.encode(dto.getPw()))
                .email(dto.getEmail())
                .nickname(dto.getNickname())
                .role(Role.USER)
                .build();
        // 문제없으면 저장
        try{
            userRepository.save(user);
            System.out.println("회원가입 저장 완료");
        // 문제 생기면 오류 발생
        }catch (DataIntegrityViolationException e){
            // DataIntegrityViolationException : 뭔가 잘못된 데이터가 바인딩 되었을때 발생하는 에러이다. SQL 문이 잘못되었거나 Data가 잘못되었을 경우
            e.printStackTrace();
        }
    }

    @Override
    public void changeNickname(String nickname){
        User user = getMyInfo();
        user.changeNickname(nickname);
        userRepository.save(user);
    }

    @Override
    public boolean checkId(String username) {
        // user id로 검색 후 존재유무를 bool값으로 전달
        Optional<User> entity = userRepository.findByUsername(username);
        return entity.isPresent();
    }

    @Override
    public boolean checkNickname(String nickname){
        // nickname으로 검색후 존재 유무를 bool값으로 전달
        Optional<User> entity = userRepository.findByNickname(nickname);
        return entity.isPresent();
    }

    @Override
    public boolean checkEmail(String email){
        // nickname으로 검색후 존재 유무를 bool값으로 전달
        Optional<User> entity = userRepository.findByEmail(email);
        return entity.isPresent();
    }

    @Override
    public TokenDto doLogin(LoginRequestDto loginDto) {
        System.out.println(loginDto);

        // 아이디와 비밀번호로 AuthenticationToken 생성
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPw());
        System.out.println("authenticationToken : " + authenticationToken);

        // CustomUserDetailsService의 loadByUserName실행
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        System.out.println("authentication : "+authentication);


        // 인증 정보 기반으로 JWT 토큰 생성
        TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);
        System.out.println("tokenDto : "+ tokenDto);

        // RefreshToken 저장
        Optional<User> entity = userRepository.findByUsername(authentication.getName());
        if (entity.isPresent()) {
            entity.get().saveToken(tokenDto.getRefreshToken());
            userRepository.save(entity.get());
        }

        return tokenDto;
    }
    @Override
    public User  getMyInfo() {
        return SecurityUtil.getCurrentUsername().flatMap(userRepository :: findByUsername).orElseThrow(UserNotFoundException::new);
    }
}


