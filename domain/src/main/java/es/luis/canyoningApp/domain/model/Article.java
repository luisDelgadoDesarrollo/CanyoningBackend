package es.luis.canyoningApp.domain.model;

import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Article {
  private Long articleId;
  private Integer articleType;
  private String title;
  private String description;
  private OffsetDateTime articleDate;
  private String image;
  private String organizer;
  private String location;
  private String price;
  private String link;
  private String instagram;
  private String twitter;
  private String tiktok;
  private String facebook;
  private String createdBy;
}
