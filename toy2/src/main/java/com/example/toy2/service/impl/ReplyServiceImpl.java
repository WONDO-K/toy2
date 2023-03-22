package com.example.toy2.service.impl;

import com.example.toy2.domain.Article;
import com.example.toy2.domain.Reply;
import com.example.toy2.domain.User;
import com.example.toy2.dto.reply.ReplyDto;
import com.example.toy2.dto.reply.ReplyRequestDto;
import com.example.toy2.repository.ArticleRepository;
import com.example.toy2.repository.ReplyRepository;
import com.example.toy2.service.ReplyService;
import com.example.toy2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    @Override
    public void updateReply(Long uid, ReplyRequestDto replyRequestDto,Long article_uid){
        Optional<Reply> reply = replyRepository.findById(uid);
        Article article = articleRepository.findById(article_uid).get();
        User user = userService.getMyInfo();
        if(reply.isPresent()){
            String upDate = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDateTime.now()).toString();
            if (reply.get().getUser() == user){
                Reply newReply = Reply.builder()
                        .uid(uid)
                        .content(replyRequestDto.getContent())
                        .upDate(upDate)
                        .user(user)
                        .article(article)
                        .nickname(user.getNickname())
                        .build();
                replyRepository.save(newReply);
            }
        }
    }
    @Override
    public void deleteReply(Long uid){
        replyRepository.deleteById(uid);
    }


    @Override
    public List<ReplyDto> getReplyList(Long uid){
        List<ReplyDto> list = replyRepository.findAllByArticleUid(uid).stream().map(m->ReplyDto.from(m))
                .collect(Collectors.toList());
        return list;
    }

}
