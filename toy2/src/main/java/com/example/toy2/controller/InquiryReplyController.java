package com.example.toy2.controller;

import com.example.toy2.dto.exception.inquiryReply.InquiryReplyRequestDto;
import com.example.toy2.service.InquiryReplyService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/inquiryReply")
@RequiredArgsConstructor
public class InquiryReplyController {

    private final InquiryReplyService inquiryReplyService;
    
    @PostMapping("create/{uid}")
    @ApiOperation(value = "문의사항 댓글 작성", notes = "문의사항에 댓글을 작성한다")
    public ResponseEntity<?> createInquiryReply(
            @RequestBody @ApiParam(value = "문의사항 댓글 작성 Dto",required = true)InquiryReplyRequestDto inquiryReplyRequestDto,
            @PathVariable @ApiParam(value = "문의사항 게시글 번호 uid",required = true) Long uid
            ){
        inquiryReplyService.createInquiryReply(uid,inquiryReplyRequestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
