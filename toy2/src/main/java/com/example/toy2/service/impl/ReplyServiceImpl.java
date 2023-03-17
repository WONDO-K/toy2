package com.example.toy2.service.impl;

import com.example.toy2.domain.Article;
import com.example.toy2.domain.Reply;
import com.example.toy2.domain.User;
import com.example.toy2.dto.article.ArticleDto;
import com.example.toy2.dto.exception.article.PostNotFoundException;
import com.example.toy2.dto.reply.ReplyRequestDto;
import com.example.toy2.repository.ArticleRepository;
import com.example.toy2.repository.ReplyRepository;
import com.example.toy2.service.ArticleService;
import com.example.toy2.service.ReplyService;
import com.example.toy2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
@Transactional
public class ReplyServiceImpl implements ReplyService {

    private final ReplyRepository replyRepository;
    private final ArticleRepository articleRepository;
    private final UserService userService;

    @Override
    public void createReply(Long uid, ReplyRequestDto replyRequestDto){
        User user = userService.getMyInfo();
        Article article = articleRepository.findById(uid).get();
        String regDate = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDateTime.now()).toString();
        Reply reply = Reply.builder()
                .content(replyRequestDto.getContent())
                .regDate(regDate)
                .nickname(user.getNickname())
                .article(article)
                .user(user)
                .build();
        replyRepository.save(reply);
    }

}
