package com.example.toy2.service;

import com.example.toy2.dto.reply.ReplyDto;
import com.example.toy2.dto.reply.ReplyRequestDto;

import java.util.List;

public interface ReplyService {
    void createReply(Long uid, ReplyRequestDto replyRequestDto);

    void updateReply(Long uid, ReplyRequestDto replyRequestDto,Long article_uid);

    void deleteReply(Long uid);

    List<ReplyDto> getReplyList(Long uid);
}
