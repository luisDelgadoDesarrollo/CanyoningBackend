package es.luis.canyoningApp.infrastructure.mapper;

import es.luis.canyoningApp.domain.model.Article;
import es.luis.canyoningApp.infrastructure.entity.ArticleEntity;
import org.mapstruct.Mapper;

@Mapper
public interface ArticleRepositoryMapper extends BaseRepositoryMapper {
  Article articleEntityToArticle(ArticleEntity articleEntity);

  ArticleEntity articleToArticleEntity(Article article);
}
