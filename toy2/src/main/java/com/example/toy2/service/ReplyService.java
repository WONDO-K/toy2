package com.example.toy2.service;

import com.example.toy2.dto.reply.ReplyRequestDto;

public interface ReplyService {
    void createReply(Long uid, ReplyRequestDto replyRequestDto);

}
