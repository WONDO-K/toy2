package com.example.toy2.repository;

import com.example.toy2.domain.Reply;
import com.example.toy2.dto.reply.ReplyDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

    List<Reply> findAllByArticleUid(Long uid);
}
