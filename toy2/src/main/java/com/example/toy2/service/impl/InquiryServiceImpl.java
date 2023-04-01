package com.example.toy2.service.impl;

import com.example.toy2.domain.Inquiry;
import com.example.toy2.domain.User;
import com.example.toy2.dto.exception.inquiry.InquiryRequestDto;
import com.example.toy2.repository.InquiryRepository;
import com.example.toy2.service.InquiryService;
import com.example.toy2.service.UserService;
import io.swagger.models.auth.In;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Service
@RequiredArgsConstructor
@Transactional
public class InquiryServiceImpl implements InquiryService {

    private final InquiryRepository inquiryRepository;

    private final UserService userService;

    @Override
    public void createInquiry(InquiryRequestDto inquiryRequestDto){
        User user = userService.getMyInfo();
        String regDate = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDateTime.now()).toString();
        Inquiry inquiry = Inquiry.builder()
                .title(inquiryRequestDto.getTitle())
                .content(inquiryRequestDto.getContent())
                .tag(inquiryRequestDto.getTag())
                .regDate(regDate)
                .user(user)
                .build();
        inquiryRepository.save(inquiry);
    }


}
