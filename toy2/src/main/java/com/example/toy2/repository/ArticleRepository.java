package com.example.toy2.repository;

import com.example.toy2.domain.Article;
import com.example.toy2.domain.enums.ArticleTag;
import com.example.toy2.dto.article.ArticleDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    List<Article> findAllByOrderByViewDesc();

    List<Article> findAllByTag(ArticleTag tag);
}
