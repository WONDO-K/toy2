package com.example.toy2.dto.reply;

import com.example.toy2.domain.Article;
import com.example.toy2.domain.Reply;
import com.example.toy2.domain.User;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@ApiModel(value = "ReplyResponseDto",description = "댓글 정보 Dto")
public class ReplyDto {
    private Long uid;
    private String content;
    private String date;
    private String nickname;

    public static ReplyDto from(Reply reply){
        String theDate;
        if(reply.getUpDate() == null){
            theDate = reply.getRegDate();
        } else {
            theDate = reply.getUpDate();
        }
        return ReplyDto.builder()
                .uid(reply.getUid())
                .content(reply.getContent())
                .date(theDate)
                .nickname(reply.getNickname())
                .build();
    }


}
