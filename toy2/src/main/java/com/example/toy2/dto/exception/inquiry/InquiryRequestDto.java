package com.example.toy2.dto.exception.inquiry;

import com.example.toy2.domain.enums.InquiryTag;
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
@ApiModel(value = "InquiryRequestDto",description = "문의사항 작성 요청 Dto")
public class InquiryRequestDto {

    @ApiModelProperty(name = "title")
    private String title;

    @ApiModelProperty(name = "content")
    private String content;

    @ApiModelProperty(name = "tag")
    private InquiryTag tag;
}
