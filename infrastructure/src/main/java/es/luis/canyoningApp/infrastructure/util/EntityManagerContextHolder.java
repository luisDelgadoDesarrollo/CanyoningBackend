package es.luis.canyoningApp.infrastructure.util;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * This class hold the EntityManager bean to be used in non-bean classes that cannot use the main
 * way of getting the bean with @Autowired.
 */
@Component
public class EntityManagerContextHolder {

    private static EntityManager entityManager;

    @Autowired
    public EntityManagerContextHolder(EntityManager entityManager) {
        EntityManagerContextHolder.entityManager = entityManager;
    }

    public static EntityManager getInstance() {
        return entityManager;
    }
}
