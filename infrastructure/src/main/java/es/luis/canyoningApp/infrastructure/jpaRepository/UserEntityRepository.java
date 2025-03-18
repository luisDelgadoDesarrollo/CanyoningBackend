package es.luis.canyoningApp.infrastructure.jpaRepository;

import es.luis.canyoningApp.infrastructure.entity.UserEntity;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserEntityRepository
    extends CrudRepository<UserEntity, Long>, PagingAndSortingRepository<UserEntity, Long> {
  Optional<UserEntity> findUserByEmail(String email);

  @Query(
      "SELECT ue FROM UserEntity ue "
          + " WHERE (:email IS NULL OR ue.email LIKE %:email%) "
          + " AND (:name IS NULL OR ue.name LIKE %:name%) "
          + " AND (:location IS NULL OR ue.location LIKE %:location%)")
  Page<UserEntity> getUsers(String email, String name, String location, Pageable pageable);
}
