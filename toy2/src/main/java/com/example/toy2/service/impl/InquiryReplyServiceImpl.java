package com.example.toy2.service.impl;

import com.example.toy2.domain.Inquiry;
import com.example.toy2.domain.InquiryReply;
import com.example.toy2.domain.User;
import com.example.toy2.dto.inquiryReply.InquiryReplyRequestDto;
import com.example.toy2.repository.InquiryReplyRepository;
import com.example.toy2.repository.InquiryRepository;
import com.example.toy2.service.InquiryReplyService;
import com.example.toy2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
@Transactional
public class InquiryReplyServiceImpl implements InquiryReplyService {

    private final InquiryReplyRepository inquiryReplyRepository;

    private final InquiryRepository inquiryRepository;

    private final UserService userService;


    @Override
    public void createInquiryReply(Long uid, InquiryReplyRequestDto inquiryReplyRequestDto) {
        User user = userService.getMyInfo();
        Inquiry inquiry = inquiryRepository.findById(uid).get();
        String theDate = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDateTime.now()).toString();
        InquiryReply inquiryReply = InquiryReply.builder()
                .content(inquiryReplyRequestDto.getContent())
                .regDate(theDate)
                .nickname(user.getNickname())
                .inquiry(inquiry)
                .user(user)
                .build();
        inquiryReplyRepository.save(inquiryReply);
    }
}
