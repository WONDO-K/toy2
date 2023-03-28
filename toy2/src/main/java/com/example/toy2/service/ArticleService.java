package com.example.toy2.service;


import com.example.toy2.domain.Article;
import com.example.toy2.dto.article.ArticleDto;
import com.example.toy2.dto.article.ArticleRequestDto;

import java.util.List;
import java.util.Optional;

public interface ArticleService {

    void createArticle(ArticleRequestDto articleRequestDto);

    void updateArticle(Long uid, ArticleRequestDto articleRequestDto);

    List<ArticleDto> getArticleList();

    void deleteArticle(Long uid);

    ArticleDto getArticle(Long uid);

    List<ArticleDto> getSortArticleList();
}
