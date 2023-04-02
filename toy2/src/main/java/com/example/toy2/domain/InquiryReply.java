package com.example.toy2.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Inquiry_Replies")
public class InquiryReply {
    @Id
    @GeneratedValue
    @Column(name = "uid")
    private Long uid;

    @Column(name = "content")
    private String content;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "reg_date")
    private String regDate;

    @Column(name = "up_date")
    private String upDate;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_uid")
    private User user;

    @ManyToOne(targetEntity = Inquiry.class,fetch = FetchType.LAZY)
    @JoinColumn(name = "Inquiry_uid")
    private Inquiry inquiry;

}
