package com.example.toy2.service;

import com.example.toy2.dto.exception.inquiry.InquiryDto;
import com.example.toy2.dto.exception.inquiry.InquiryRequestDto;

import java.util.List;

public interface InquiryService {
    void createInquiry(InquiryRequestDto inquiryRequestDto);

    List<InquiryDto> getInquiryList();

    InquiryDto getInquiry(Long uid);
}
