package com.example.toy2.dto.reply;

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
@ApiModel(value = "ReplyRequestDto" ,description = "댓글 작성 요청 Dto")
public class ReplyRequestDto {

    @ApiModelProperty(name = "content")
    private String content;

}
