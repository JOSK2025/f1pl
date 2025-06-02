package com.josk.f1pp.repository;

import com.josk.f1pp.model.Article;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    @Query(value = """
        SELECT * FROM (
            SELECT a.*, ROWNUM rnum FROM (
                SELECT *
                FROM articles
                ORDER BY created_at DESC
            ) a
            WHERE ROWNUM <= (:offset + :pageSize)
        )
        WHERE rnum > :offset
    """, nativeQuery = true)
    List<Article> findAllByOrderByCreatedAtDesc(@Param("offset") int offset, @Param("pageSize") int pageSize);

    @Query(value = "SELECT COUNT(*) FROM articles", nativeQuery = true)
    long countArticles();

    Article findById(long id);
}
