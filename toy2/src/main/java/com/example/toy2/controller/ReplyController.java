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
    @PutMapping("/{article_uid}/{uid}")
    @ApiOperation(value = "댓글 수정", notes = "댓글을 수정한다.")
    public ResponseEntity<?> updateReply(
            @RequestBody @ApiParam(value = "댓글 수정 Dto",required = true) ReplyRequestDto replyRequestDto,
            @PathVariable @ApiParam(value = "게시글 번호 uid",required = true) Long article_uid,
            @PathVariable @ApiParam(value = "댓글 번호 uid",required = true) Long uid

    ){
        replyService.updateReply(uid,replyRequestDto,article_uid);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("/delete/{uid}")
    @ApiOperation(value = "댓글 삭제", notes = "댓글을 삭제한다.")
    public ResponseEntity<?> deleteReply(@PathVariable Long uid){
        replyService.deleteReply(uid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/list/{uid}")
    @ApiOperation(value = "댓글 리스트 조회", notes = "댓글 리스트를 조회한다.")
    public ResponseEntity<?> getReplyList(
            @PathVariable @ApiParam(value = "게시글 uid",required = true) Long uid
    ){
        return new ResponseEntity<>(replyService.getReplyList(uid),HttpStatus.OK);
    }
}
