package com.josk.f1pp.controller;

import com.josk.f1pp.dto.ArticleDto;
import com.josk.f1pp.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ArticlesController {

    private ArticleService articleService;

    @Autowired
    public ArticlesController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/articles")
    public ResponseEntity<Page<ArticleDto>> allArticles(@RequestParam(defaultValue = "0") int page,
                                                        @RequestParam(defaultValue = "5") int size){
        Page<ArticleDto> articles = articleService.findArticles(PageRequest.of(page, size));
        return ResponseEntity.ok(articles);
    }

    @GetMapping("/article/{articleId}")
    public ResponseEntity<ArticleDto> getArticle(@PathVariable("articleId") long articleId){
        ArticleDto article = articleService.findArticleById(articleId);
        return ResponseEntity.ok(article);
    }
}