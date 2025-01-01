package es.luis.canyoningApp.domain.repository;

import es.luis.canyoningApp.domain.model.Ad;

import java.time.LocalDate;

public interface AdRepository {
    Ad getAdByDate(LocalDate now);
}
