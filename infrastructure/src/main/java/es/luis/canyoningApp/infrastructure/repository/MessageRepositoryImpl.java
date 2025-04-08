package es.luis.canyoningApp.infrastructure.repository;

import es.luis.canyoningApp.domain.model.Message;
import es.luis.canyoningApp.domain.repository.MessageRepository;
import es.luis.canyoningApp.domain.service.CanyonService;
import es.luis.canyoningApp.domain.service.UserService;
import es.luis.canyoningApp.domain.util.AuthenticatedUserBase;
import es.luis.canyoningApp.infrastructure.entity.CanyonMessageEntity;
import es.luis.canyoningApp.infrastructure.jpaRepository.CanyonMessageEntityRepository;
import es.luis.canyoningApp.infrastructure.mapper.MessageRepositoryMapper;
import es.luis.canyoningApp.infrastructure.mapper.SimpleCanyonRepositoryMapper;
import es.luis.canyoningApp.infrastructure.mapper.UserRepositoryMapper;
import jakarta.transaction.Transactional;
import java.time.OffsetDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public class MessageRepositoryImpl extends AuthenticatedUserBase implements MessageRepository {

  @Autowired private MessageRepositoryMapper messageRepositoryMapper;

  @Autowired private CanyonMessageEntityRepository canyonMessageEntityRepository;

  @Autowired private UserService userService;

  @Autowired private UserRepositoryMapper userRepositoryMapper;

  @Autowired private CanyonService canyonService;

  @Autowired private SimpleCanyonRepositoryMapper simpleCanyonRepositoryMapper;

  @Override
  public void postCanyonMessage(Message message) {
    CanyonMessageEntity canyonMessageEntity =
        messageRepositoryMapper.messageToCanyonMessageEntity(message);
    canyonMessageEntity.setUser(
        userRepositoryMapper.userToUserEntity(userService.getUserById(getAuthenticatedUserId())));
    canyonMessageEntity.setCanyon(
        simpleCanyonRepositoryMapper.simpleCanyonToSimpleCanyonEntity(
            canyonService.getCanyonById(message.getPlaceId())));
    canyonMessageEntity.setSendAt(OffsetDateTime.now());
    canyonMessageEntityRepository.save(canyonMessageEntity);
  }

  @Override
  public Page<Message> getCanyonMessages(Long activityId, Pageable pageable) {
    return canyonMessageEntityRepository
        .findAllByCanyon(activityId, pageable)
        .map(messageRepositoryMapper::canyonMessageEntityToMessage);
  }

  @Override
  public Message getCanyonMessage(Long messageId) {
    return messageRepositoryMapper.canyonMessageEntityToMessage(
        canyonMessageEntityRepository.findById(messageId).orElseThrow());
  }

  @Override
  public void updateMessage(Message message) {

    CanyonMessageEntity canyonMessageEntity =
        messageRepositoryMapper.messageToCanyonMessageEntity(message);
    canyonMessageEntityRepository.save(canyonMessageEntity);
  }

  @Override
  public void deleteCanyonMessage(Long messageId) {
    canyonMessageEntityRepository.deleteById(messageId);
  }
}
