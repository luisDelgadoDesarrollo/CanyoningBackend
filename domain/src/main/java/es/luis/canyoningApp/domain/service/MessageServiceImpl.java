package es.luis.canyoningApp.domain.service;

import es.luis.canyoningApp.domain.model.ActivityType;
import es.luis.canyoningApp.domain.model.Message;
import es.luis.canyoningApp.domain.repository.MessageRepository;
import es.luis.canyoningApp.domain.util.AuthenticatedUserBase;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class MessageServiceImpl extends AuthenticatedUserBase implements MessageService {

  @Autowired private MessageRepository messageRepository;

  @Override
  public void postMessage(Message message) {
    // there is a switch because its thinked to be mor activity types like climb or dive
    if (message.getDate() == null || message.getDate().isAfter(LocalDate.now())) {
      message.setDate(LocalDate.now());
    }
    switch (message.getTypePlace()) {
      case ActivityType.CANYON:
        if (!message.getMessage().isEmpty()
            || !ObjectUtils.isEmpty(message.getFlow())
            || !ObjectUtils.isEmpty(message.getTemperature())) {
          messageRepository.postCanyonMessage(message);
        }
        break;
    }
  }

  @Override
  public Page<Message> getMessage(ActivityType activityType, Long activityId, Pageable pageable) {
    switch (activityType) {
      case ActivityType.CANYON:
        pageable =
            PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                Sort.Direction.valueOf("DESC"),
                "canyonMessageId");
        return messageRepository.getCanyonMessages(activityId, pageable);
    }
    return null;
  }

  @Override
  public void updateMessage(Long messageId, Message message) {
    switch (message.getTypePlace()) {
      case ActivityType.CANYON:
        Message canyonMessage = messageRepository.getCanyonMessage(messageId);
        if (canyonMessage.getUser().getUserId().equals(getAuthenticatedUserId())
            && canyonMessage.getTypePlace().equals(message.getTypePlace())
            && canyonMessage.getPlaceId().equals(message.getPlaceId())) {
          canyonMessage.setMessage(message.getMessage());
          canyonMessage.setFlow(message.getFlow());
          canyonMessage.setTemperature(message.getTemperature());
          messageRepository.updateMessage(canyonMessage);
        }
    }
  }

  @Override
  public void deleteMessage(Long messageId, ActivityType activityType) {
    switch (activityType) {
      case ActivityType.CANYON:
        Message canyonMessage = messageRepository.getCanyonMessage(messageId);
        if (canyonMessage.getUser().getUserId().equals(getAuthenticatedUserId())) {
          messageRepository.deleteCanyonMessage(messageId);
        }
    }
  }
}
