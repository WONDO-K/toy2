package com.example.toy2.controller;


import com.example.toy2.dto.inquiry.InquiryDto;
import com.example.toy2.dto.inquiry.InquiryRequestDto;
import com.example.toy2.service.InquiryService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inquiry")
@RequiredArgsConstructor
public class InquiryController {

    private final InquiryService inquiryService;

    @PostMapping("/create")
    @ApiOperation(value = "문의사항 작성",notes = "문의사항을 작성한다.")
    public ResponseEntity<?> createInquiry(
            @RequestBody @ApiParam(value = "공지사항 생성 Dto",required = true) InquiryRequestDto inquiryRequestDto ){
        inquiryService.createInquiry(inquiryRequestDto);
        return new ResponseEntity<>(HttpStatus.OK);

    }
    @PutMapping("/update/{uid}")
    @ApiOperation(value = "문의사항 수정",notes = "문의사항을 수정한다.")
    public ResponseEntity<?> updateInquiry(
            @RequestBody @ApiParam(value = "문의사항 수정 Dto",required = true) InquiryRequestDto inquiryRequestDto,
            @PathVariable @ApiParam(value = "문의사항 uid",required = true) Long uid){
        inquiryService.updateInquiry(uid,inquiryRequestDto);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @GetMapping("/any/InquiryList")
    @ApiOperation(value = "문의사항 리스트 조회",notes = "문의사항 리스트를 조회한다.")
    public ResponseEntity<?> getInquiryList(){
        return new ResponseEntity<>(inquiryService.getInquiryList(),HttpStatus.OK);
    }

    @GetMapping("{uid}")
    @ApiOperation(value = "문의사항 게시글 조회",notes = "문의사항 게시글을 조회한다.")
    public ResponseEntity<InquiryDto> getInquiry(@PathVariable Long uid){
        return new ResponseEntity<>(inquiryService.getInquiry(uid),HttpStatus.OK);
    }

    @DeleteMapping("delete/{uid}")
    @ApiOperation(value = "문의사항 게시글 삭제",notes = "문의사항 게시글을 삭제한다.")
    public ResponseEntity<?> deleteInquiry(
            @PathVariable @ApiParam(value = "문의사항 게시글 uid",required = true) Long uid
    ){
        inquiryService.deleteInquiry(uid);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
