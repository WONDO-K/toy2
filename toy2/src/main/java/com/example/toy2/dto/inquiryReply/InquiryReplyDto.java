package com.example.toy2.dto.inquiryReply;

import com.example.toy2.domain.InquiryReply;
import io.swagger.annotations.ApiModel;
import lombok.*;

@Getter
@Builder
@ApiModel(value = "InquiryReplyResponseDto",description = "문의사항 댓글 요청 Dto")
public class InquiryReplyDto {
    private Long uid;
    private String content;
    private String date;
    private String nickname;

    public static InquiryReplyDto from(InquiryReply inquiryReply){
        String theDate;
        if(inquiryReply.getUpDate() == null){
            theDate = inquiryReply.getRegDate();
        } else {
            theDate = inquiryReply.getUpDate();
        }
        return InquiryReplyDto.builder()
                .uid(inquiryReply.getUid())
                .content(inquiryReply.getContent())
                .date(theDate)
                .nickname(inquiryReply.getUser().getNickname())
                .build();
    }
}
