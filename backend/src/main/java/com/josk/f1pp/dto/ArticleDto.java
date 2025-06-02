package com.josk.f1pp.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ArticleDto {
    private long id;
    private String title;
    private String content;
    private String imageUrl;
    private String authorName;
    private LocalDateTime createdAt;
}
