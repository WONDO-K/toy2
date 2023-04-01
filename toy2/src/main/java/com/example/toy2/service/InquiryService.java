package com.example.toy2.service;

import com.example.toy2.dto.exception.inquiry.InquiryRequestDto;

public interface InquiryService {
    void createInquiry(InquiryRequestDto inquiryRequestDto);
}
