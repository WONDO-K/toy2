package com.example.toy2.dto.exception.user;


import com.example.toy2.dto.exception.CustomException;
import com.example.toy2.dto.exception.ErrorCode;

public class DuplicateEmailException extends CustomException {
    public DuplicateEmailException() {
        super(ErrorCode.DUPLICATE_EMAIL);
    }
}
