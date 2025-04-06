package es.luis.canyoningApp.application.rest.controller;

import es.luis.canyoningApp.application.rest.api.ArticleApi;
import es.luis.canyoningApp.application.rest.mapper.ArticleControllerMapper;
import es.luis.canyoningApp.application.rest.model.ArticleDto;
import es.luis.canyoningApp.application.rest.model.SimpleArticleDto;
import es.luis.canyoningApp.domain.model.Article;
import es.luis.canyoningApp.domain.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ArticleController extends BaseController implements ArticleApi {

    @Autowired
    private ArticleControllerMapper articleControllerMapper;

    @Autowired
    private ArticleService articleService;

    @Override
    public ResponseEntity<Void> createArticle(ArticleDto articleDto) {
        articleService.createArticle(articleControllerMapper.articleDtoToArticle(articleDto));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public ResponseEntity<Void> deleteArticle(Long articleId) {
        articleService.deleteArticle(articleId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<ArticleDto> getArticleById(Long articleId) {
        return ResponseEntity.ok(
                articleControllerMapper.articleToArticleDto(articleService.getArticleById(articleId)));
    }

    @Override
    public ResponseEntity<List<SimpleArticleDto>> getAtricles(
            String title,
            String location,
            String organizer,
            Integer page,
            Integer size,
            String sort,
            Pageable pageable) {
        Page<Article> articles = articleService.getArticles(title, location, organizer, pageable);
        addPaginationHeadersToResponse(articles);
        return ResponseEntity.ok(
                articles.map(articleControllerMapper::articleToSimpleArticleDto).toList());
    }

    @Override
    public ResponseEntity<Void> updateArticle(Long articleId, ArticleDto articleDto) {
        articleService.updateArticle(
                articleId, articleControllerMapper.articleDtoToArticle(articleDto));
        return ResponseEntity.ok().build();
    }
}
