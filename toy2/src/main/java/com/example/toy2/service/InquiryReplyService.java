package com.example.toy2.service;

import com.example.toy2.dto.inquiryReply.InquiryReplyRequestDto;

public interface InquiryReplyService {

    void createInquiryReply(Long uid, InquiryReplyRequestDto inquiryReplyRequestDto);
}
