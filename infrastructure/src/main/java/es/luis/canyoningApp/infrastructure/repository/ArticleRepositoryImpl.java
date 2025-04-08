package es.luis.canyoningApp.infrastructure.repository;

import es.luis.canyoningApp.domain.model.Article;
import es.luis.canyoningApp.domain.repository.ArticleRepository;
import es.luis.canyoningApp.infrastructure.jpaRepository.ArticleEntityRepository;
import es.luis.canyoningApp.infrastructure.mapper.ArticleRepositoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class ArticleRepositoryImpl implements ArticleRepository {

  @Autowired private ArticleEntityRepository articleEntityRepository;

  @Autowired private ArticleRepositoryMapper articleRepositoryMapper;

  @Override
  public void createArticle(Article article) {
    articleEntityRepository.save(articleRepositoryMapper.articleToArticleEntity(article));
  }

  @Override
  public void deleteArticle(Long articleId) {
    articleEntityRepository.deleteById(articleId);
  }

  @Override
  public Article getArticleById(Long articleId) {
    return articleEntityRepository
        .findById(articleId)
        .map(articleRepositoryMapper::articleEntityToArticle)
        .orElseThrow();
  }

  @Override
  public Page<Article> getArticles(
      String title, String location, String organizer, Pageable pageable) {
    return articleEntityRepository
        .getArticles(title, location, organizer, pageable)
        .map(articleRepositoryMapper::articleEntityToArticle);
  }

  @Override
  public void updateArticle(Article article) {
    articleEntityRepository.save(articleRepositoryMapper.articleToArticleEntity(article));
  }
}
