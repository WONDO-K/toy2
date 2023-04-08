package com.example.toy2.service.impl;

import com.example.toy2.domain.Inquiry;
import com.example.toy2.domain.User;
import com.example.toy2.dto.exception.article.PostNotFoundException;
import com.example.toy2.dto.inquiry.InquiryDto;
import com.example.toy2.dto.inquiry.InquiryRequestDto;
import com.example.toy2.repository.InquiryRepository;
import com.example.toy2.service.InquiryService;
import com.example.toy2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import javax.security.sasl.AuthenticationException;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


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
    @Override
    public void updateInquiry(Long uid,InquiryRequestDto inquiryRequestDto){
        Optional<Inquiry> inquiry = inquiryRepository.findById(uid);
        if (inquiry.isPresent()){
            String upDate = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDateTime.now()).toString();
            if (inquiry.get().getUser() == userService.getMyInfo()){
                Inquiry newInquiry = Inquiry.builder()
                        .uid(uid)
                        .title(inquiryRequestDto.getTitle())
                        .content(inquiryRequestDto.getContent())
                        .tag(inquiryRequestDto.getTag())
                        .upDate(upDate)
                        .user(userService.getMyInfo())
                        .build();
                inquiryRepository.save(newInquiry);
            }
        }
    }
    @Override
    public void deleteInquiry(Long uid){
            if (inquiryRepository.findById(uid).get().getUser() != userService.getMyInfo()){
                throw new AccessDeniedException("작성자가 아닌 사람은 삭제할 수 없습니다.");
            } else {
                inquiryRepository.deleteById(uid);
            }
    }

    @Override
    public List<InquiryDto> getInquiryList() {
        List<InquiryDto> inquiryList = inquiryRepository.findAll().stream().map(m->InquiryDto.from(m))
                .collect(Collectors.toList());
        return inquiryList;
    }

    @Override
    public InquiryDto getInquiry(Long uid) {
        Inquiry inquiry = inquiryRepository.findById(uid).orElseThrow(PostNotFoundException::new);
        return InquiryDto.from(inquiry);
    }


}
