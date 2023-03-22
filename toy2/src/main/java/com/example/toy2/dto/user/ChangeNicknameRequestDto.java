package com.example.toy2.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "ChangeNicknameRequestDto", description = "닉네임 변경 요청")
public class ChangeNicknameRequestDto {
    @ApiModelProperty(name = "nickname")
    private String nickname;
}
