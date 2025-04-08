package es.luis.canyoningApp.domain.service;

import es.luis.canyoningApp.domain.exception.BadRequestException;
import es.luis.canyoningApp.domain.model.Article;
import es.luis.canyoningApp.domain.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ArticleServiceImpl extends BaseService implements ArticleService {

  @Autowired private ArticleRepository articleRepository;

  @Override
  public void createArticle(Article article) {
    if (article.getArticleType() == null || article.getTitle() == null) {
      throw new BadRequestException();
    }
    article.setCreatedBy(getAuthenticatedUserEmail());
    article.setImage(article.getTitle().trim().replace(" ", "_") + ".jpg");
    articleRepository.createArticle(article);
  }

  @Override
  public void deleteArticle(Long articleId) {
    articleRepository.deleteArticle(articleId);
  }

  @Override
  public Article getArticleById(Long articleId) {
    return articleRepository.getArticleById(articleId);
  }

  @Override
  public Page<Article> getArticles(
      String title, String location, String organizer, Pageable pageable) {
    return articleRepository.getArticles(title, location, organizer, pageable);
  }

  @Override
  public void updateArticle(Long articleId, Article article) {
    Article articleById = getArticleById(articleId);
    article.setCreatedBy(articleById.getCreatedBy());
    article.setArticleId(articleId);
    articleRepository.updateArticle(article);
  }
}
