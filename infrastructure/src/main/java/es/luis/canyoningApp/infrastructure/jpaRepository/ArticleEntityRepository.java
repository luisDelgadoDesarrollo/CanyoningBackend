package es.luis.canyoningApp.infrastructure.jpaRepository;

import es.luis.canyoningApp.infrastructure.entity.ArticleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ArticleEntityRepository extends JpaRepository<ArticleEntity, Long> {

    @Query(
            "SELECT ae FROM ArticleEntity ae "
                    + "WHERE (LOWER(ae.title) LIKE LOWER(CONCAT('%', :title, '%')) OR :title IS NULL) "
                    + "AND (LOWER(ae.location) LIKE LOWER(CONCAT('%', :location, '%')) OR :location IS NULL) "
                    + "AND (LOWER(ae.organizer) LIKE LOWER(CONCAT('%', :organizer, '%')) OR :organizer IS NULL) "
                    + "order by ae.articleId desc")
    Page<ArticleEntity> getArticles(
            String title, String location, String organizer, Pageable pageable);
}
