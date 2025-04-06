package es.luis.canyoningApp.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@Entity
@Table(name = "article")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArticleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "articleId", nullable = false, updatable = false)
    private Long articleId;

    @Column(name = "articleType", nullable = false)
    private Integer articleType;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "articleDate")
    private OffsetDateTime articleDate;

    @Column(name = "image")
    private String image;

    @Column(name = "organizer")
    private String organizer;

    @Column(name = "location")
    private String location;

    @Column(name = "price")
    private String price;

    @Column(name = "link")
    private String link;

    @Column(name = "instagram")
    private String instagram;

    @Column(name = "twitter")
    private String twitter;

    @Column(name = "tiktok")
    private String tiktok;

    @Column(name = "facebook")
    private String facebook;

    @Column(name = "createdAt")
    private OffsetDateTime createdAt;

    @Column(name = "createdBy")
    private String createdBy;

    @PrePersist
    public void defaultValues() {
        if (createdAt == null) {
            createdAt = OffsetDateTime.now();
        }
    }
}
