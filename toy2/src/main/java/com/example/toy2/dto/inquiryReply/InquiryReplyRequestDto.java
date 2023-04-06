package com.example.toy2.dto.inquiryReply;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "InquiryReplyRequestDto" ,description = "문의사항 댓글 요청 Dto")
public class InquiryReplyRequestDto {

    @ApiModelProperty(name = "content")
    private String content;

}
