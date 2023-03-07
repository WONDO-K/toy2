package com.example.toy2.dto.exception.user;


import com.example.toy2.dto.exception.CustomException;
import com.example.toy2.dto.exception.ErrorCode;

public class UserNotFoundException extends CustomException {
    public UserNotFoundException(){
        super(ErrorCode.USER_NOT_FOUND);
    }
}
