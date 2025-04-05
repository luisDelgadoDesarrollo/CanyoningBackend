package es.luis.canyoningApp.application.rest.mapper;

import es.luis.canyoningApp.application.rest.model.ArticleDto;
import es.luis.canyoningApp.application.rest.model.SimpleArticleDto;
import es.luis.canyoningApp.domain.model.Article;
import org.mapstruct.Mapper;

@Mapper
public interface ArticleControllerMapper {

  ArticleDto articleToArticleDto(Article article);

  Article articleDtoToArticle(ArticleDto articleDto);

  SimpleArticleDto articleToSimpleArticleDto(Article article);
}
