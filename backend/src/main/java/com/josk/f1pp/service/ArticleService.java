package com.josk.f1pp.service;

import com.josk.f1pp.dto.ArticleDto;
import com.josk.f1pp.mapper.ArticleMapper;
import com.josk.f1pp.model.Article;
import com.josk.f1pp.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleService {
    private ArticleRepository articleRepository;

    @Autowired
    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Page<ArticleDto> findArticles(Pageable pageable) {
        int offset = (int) pageable.getOffset();
        int pageSize = pageable.getPageSize();
        List<Article> articles = articleRepository.findAllByOrderByCreatedAtDesc(offset, pageSize);
        long totalArticles = articleRepository.countArticles();
        List<ArticleDto> articlesDto = articles.stream().map(ArticleMapper::mapToArticleDto).collect(Collectors.toList());
        return new PageImpl<>(articlesDto, pageable, totalArticles);
    }

    public ArticleDto findArticleById(long id) {
        return ArticleMapper.mapToArticleDto(articleRepository.findById(id));
    }
}
