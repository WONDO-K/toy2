package com.example.toy2.dto.inquiry;



import com.example.toy2.domain.Inquiry;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@ApiModel(value = "InquiryResponseDto",description = "문의사항 정보 Dto")
public class InquiryDto {
    private Long uid;
    private String title;
    private String date;
    private String tag;
    private String nickname;

    public static InquiryDto from(Inquiry inquiry){
        String theDate;
        if(inquiry.getUpDate() == null){
            theDate = inquiry.getRegDate();
        }else {
            theDate =  inquiry.getUpDate();
        }
        return InquiryDto.builder()
                .uid(inquiry.getUid())
                .title(inquiry.getTitle())
                .date(theDate)
                .tag(inquiry.getTag().toString())
                .nickname(inquiry.getUser().getNickname())
                .build();
    }
}
