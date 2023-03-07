package com.example.toy2.dto.exception.common;

import com.example.toy2.dto.exception.CustomException;
import com.example.toy2.dto.exception.ErrorCode;
import org.springframework.validation.Errors;

//BindingResult를 만들어 주는 역할
public class InvalidParameterException extends CustomException {

    private final Errors errors;

    public InvalidParameterException(Errors errors){
        super(ErrorCode.INVALID_PARAMETER);
        this.errors = errors;
    }
}
