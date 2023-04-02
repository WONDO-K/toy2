package com.example.toy2.service;

import com.example.toy2.domain.Inquiry;
import com.example.toy2.dto.exception.inquiryReply.InquiryReplyRequestDto;

public interface InquiryReplyService {

    void createInquiryReply(Long uid, InquiryReplyRequestDto inquiryReplyRequestDto);
}
