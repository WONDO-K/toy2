package com.example.toy2.dto.exception.user;

import com.example.toy2.dto.exception.CustomException;
import com.example.toy2.dto.exception.ErrorCode;

public class DuplicateIdException extends CustomException {

    public DuplicateIdException() {
        super(ErrorCode.DUPLICATE_ID);
    }
}
