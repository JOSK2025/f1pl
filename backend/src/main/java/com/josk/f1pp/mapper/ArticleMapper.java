package com.josk.f1pp.mapper;

import com.josk.f1pp.dto.ArticleDto;
import com.josk.f1pp.model.Article;

public class ArticleMapper {
    public static ArticleDto mapToArticleDto(Article article) {
        return ArticleDto.builder()
                .id(article.getId())
                .title(article.getTitle())
                .content(article.getContent())
                .imageUrl(article.getImageUrl())
                .authorName(article.getAuthor().getUsername())
                .createdAt(article.getCreatedAt())
                .build();

    }
}
