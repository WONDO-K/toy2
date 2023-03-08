package com.example.toy2.service;


import com.example.toy2.dto.article.ArticleRequestDto;

public interface ArticleService {

    void createArticle(ArticleRequestDto articleRequestDto);

    void updateArticle(Long uid, ArticleRequestDto articleRequestDto);
}
