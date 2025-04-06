package es.luis.canyoningApp.domain.repository;

import es.luis.canyoningApp.domain.model.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ArticleRepository {

    void createArticle(Article article);

    void deleteArticle(Long articleId);

    Article getArticleById(Long articleId);

    Page<Article> getArticles(String title, String location, String organizer, Pageable pageable);

    void updateArticle(Article article);
}
