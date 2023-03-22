package com.example.toy2.service;


import com.example.toy2.domain.User;
import com.example.toy2.dto.TokenDto;
import com.example.toy2.dto.user.ChangeNicknameRequestDto;
import com.example.toy2.dto.user.LoginRequestDto;
import com.example.toy2.dto.user.SignUpRequestDto;



public interface UserService {

    public void signup(SignUpRequestDto dto);

    boolean checkId(String userid);

    boolean checkNickname(String nickname);

    boolean checkEmail(String email);

    TokenDto doLogin(LoginRequestDto requestDto);

    User getMyInfo();

    void changeNickname(String nickname);
}
