package es.luis.canyoningApp.infrastructure.jpaRepository;

import es.luis.canyoningApp.infrastructure.entity.UserEntity;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserEntityRepository
    extends CrudRepository<UserEntity, Long>, PagingAndSortingRepository<UserEntity, Long> {
  UserEntity findUserByEmail(String email);

  @Query(
      "SELECT ue FROM UserEntity ue "
          + " WHERE (:email IS NULL OR ue.email LIKE %:email%) "
          + " AND (:name IS NULL OR ue.name LIKE %:name%) "
          + " AND (:location IS NULL OR ue.location LIKE %:location%)")
  List<UserEntity> getUsers(String email, String name, String location);
}
