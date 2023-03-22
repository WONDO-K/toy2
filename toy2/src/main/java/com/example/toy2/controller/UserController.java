package com.example.toy2.controller;

import com.example.toy2.dto.TokenDto;
import com.example.toy2.dto.exception.common.InvalidParameterException;
import com.example.toy2.dto.exception.user.DuplicateEmailException;
import com.example.toy2.dto.user.ChangeNicknameRequestDto;
import com.example.toy2.dto.user.LoginRequestDto;
import com.example.toy2.dto.user.SignUpRequestDto;
import com.example.toy2.dto.exception.user.DuplicateIdException;
import com.example.toy2.dto.exception.user.DuplicateNicknameException;
import com.example.toy2.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/auth/signup")
    @ApiOperation(value = "회원가입",notes = "회원정보를 통해 회원을 추가한다.")
    @ApiResponses({
            @ApiResponse(code=200,message = "Success", response = String.class)
    })
    public ResponseEntity<String> signup(@Valid @RequestBody SignUpRequestDto requestDto, BindingResult result) {
        if (result.hasErrors()) {
            throw new InvalidParameterException(result);
        } else if (userService.checkId(requestDto.getId())) {
            throw new DuplicateIdException();
        } else if (userService.checkNickname(requestDto.getNickname())) {
            throw new DuplicateNicknameException();
        } else if (userService.checkEmail(requestDto.getEmail())){
            throw new DuplicateEmailException();
        }
        userService.signup(requestDto);
        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }
    @PutMapping("/changenick")
    @ApiOperation(value = "닉네임 변경", notes = "닉네임을 변경한다.")
    public ResponseEntity<String> changeNickename(
            @RequestParam @ApiParam(value = "변경할 닉네임",required = true)String nickname
            ){
        userService.changeNickname(nickname);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping("/changepw")
    @ApiOperation(value = "패스워드 변경", notes = "패스워드를 변경한다.")
    private ResponseEntity<String> changePassword(
            @RequestParam @ApiParam(value = "변경할 PW", required = true) String pw
    ){
        userService.changePw(pw);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/auth/check/nickname/{nickname}")
    @ApiOperation(value = "닉네임 중복검사",notes = "해당 닉네임이 중복되는지 검사한다.")
    @ApiResponses({
            @ApiResponse(code = 200,message = "Succes",response = Boolean.class)
    })
    public ResponseEntity<Boolean> checkNickname(@PathVariable String nickname){
        return new ResponseEntity<>(userService.checkNickname(nickname),HttpStatus.OK);
    }
    @GetMapping("/auth/check/id/{id}")
    @ApiOperation(value = "아이디 중복검사", notes = "해당 아이디가 중복되는지 검사한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success", response = Boolean.class)
    })
    public ResponseEntity<Boolean> checkId(@PathVariable String username) {
        return new ResponseEntity<>(userService.checkId(username), HttpStatus.OK);
    }

    @GetMapping("/auth/check/email/{email}")
    @ApiOperation(value = "이메일 중복검사", notes = "해당 이메일이 중복되는지 검사한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success", response = Boolean.class)
    })
    public ResponseEntity<Boolean> checkEmail(@PathVariable String email) {
        return new ResponseEntity<>(userService.checkEmail(email), HttpStatus.OK);
    }
    @PostMapping("/auth/login")
    @ApiOperation(value = "로그인", notes = "아이디와 비밀번호를 통해 로그인한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success", response = TokenDto.class)
    })
    public ResponseEntity<TokenDto> doLogin(@Valid @RequestBody LoginRequestDto requestDto, BindingResult result) {
        if(result.hasErrors()) {
            throw new InvalidParameterException(result);
        }
        TokenDto tokenDto = userService.doLogin(requestDto);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Auth", tokenDto.getAccessToken());
        headers.add("Refresh", tokenDto.getRefreshToken());

        return new ResponseEntity<>(tokenDto, headers, HttpStatus.OK);
    }
}
