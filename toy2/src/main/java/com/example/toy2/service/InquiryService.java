package com.example.toy2.service;

import com.example.toy2.dto.inquiry.InquiryDto;
import com.example.toy2.dto.inquiry.InquiryRequestDto;

import java.util.List;

public interface InquiryService {
    void createInquiry(InquiryRequestDto inquiryRequestDto);

    List<InquiryDto> getInquiryList();

    InquiryDto getInquiry(Long uid);

    void updateInquiry(Long uid,InquiryRequestDto inquiryRequestDto);

    void deleteInquiry(Long uid);
}
