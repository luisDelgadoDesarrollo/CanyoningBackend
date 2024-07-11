package es.luis.canyoningApp.infrastructure.repository;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaUpdate;
import jakarta.persistence.criteria.Root;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.OffsetDateTime;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

public abstract class BaseRepository {

  @PersistenceContext private EntityManager entityManager;

  /**
   * Cancels the entity setting canceledAt and canceledBy fields of the class. If entity has a
   * composite primary key, provide the PrimaryKeys object that contains all identifiers of the
   * entity.
   *
   * @param entityClass Class of the entity. It must be annotated with @IdClass or have one @Id
   *     field.
   * @param primaryKey Primary key of the entity to cancel. It must be the same class of
   *     the @IdClass o @Id of the entity.
   * @param canceledBy The username to set in canceledBy field.
   * @throws IllegalArgumentException If any parameter does not complain with requirements.
   * @throws RuntimeException If there is any failure at reflection or calling to entity manager.
   */
  void cancelEntity(Class<?> entityClass, Object primaryKey, String canceledBy)
      throws RuntimeException {
    if (!entityClass.isAnnotationPresent(Entity.class)) {
      throw new IllegalArgumentException(entityClass + " is not an entity.");
    }
    if (primaryKey == null) {
      throw new IllegalArgumentException("Primary key cannot be null.");
    }
    if (!StringUtils.hasText(canceledBy)) {
      throw new IllegalArgumentException("Canceled by username cannot be empty.");
    }

    // For composite primary key
    if (entityClass.isAnnotationPresent(IdClass.class)) {
      Object entity = entityManager.find(entityClass, primaryKey);
      try {
        Method setCanceledAt =
            ReflectionUtils.findMethod(entityClass, "setCanceledAt", OffsetDateTime.class);
        Method setCanceledBy =
            ReflectionUtils.findMethod(entityClass, "setCanceledBy", String.class);
        if (setCanceledAt == null || setCanceledBy == null) {
          throw new IllegalArgumentException(
              entityClass + " does not have setCanceledAt and/or setCanceledBy methods.");
        }
        setCanceledAt.invoke(entity, OffsetDateTime.now());
        setCanceledBy.invoke(entity, canceledBy);
      } catch (IllegalAccessException | InvocationTargetException e) {
        throw new RuntimeException(e);
      }
      entityManager.merge(entity);
      return;
    } else {
      // Find the field that have @Id annotation
      for (Field declaredField : entityClass.getDeclaredFields()) {
        if (declaredField.isAnnotationPresent(Id.class)) {
          CriteriaBuilder cb = entityManager.getCriteriaBuilder();
          CriteriaUpdate<?> criteriaUpdate = cb.createCriteriaUpdate(entityClass);
          Root<?> root = criteriaUpdate.getRoot();
          criteriaUpdate
              .set("canceledAt", OffsetDateTime.now())
              .set("canceledBy", canceledBy)
              .where(cb.equal(root.get(declaredField.getName()), primaryKey));
          Session session = (Session) entityManager.getDelegate();
          if (!session.isJoinedToTransaction()) {
            Transaction transaction = session.beginTransaction();
            session.createMutationQuery(criteriaUpdate).executeUpdate();
            transaction.commit();
          } else {
            session.createMutationQuery(criteriaUpdate).executeUpdate();
          }
          return;
        }
      }
    }
    throw new RuntimeException("Entity cannot be canceled.");
  }
}
