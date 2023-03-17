package com.example.toy2.controller;

import com.example.toy2.dto.reply.ReplyRequestDto;
import com.example.toy2.service.ReplyService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/reply")
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyService replyService;

    @PostMapping("/create/{uid}")
    @ApiOperation(value = "댓글 작성",notes = "댓글을 작성한다.")
    public ResponseEntity<?> createReply(
            @RequestBody @ApiParam(value = "댓글 작성 Dto",required = true) ReplyRequestDto replyRequestDto,
            @PathVariable @ApiParam(value = "게시글 번호 uid",required = true) Long uid
    ){
        replyService.createReply(uid,replyRequestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
