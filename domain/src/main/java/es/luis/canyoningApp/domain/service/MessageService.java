package es.luis.canyoningApp.domain.service;

import es.luis.canyoningApp.domain.model.ActivityType;
import es.luis.canyoningApp.domain.model.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MessageService {

    void postMessage(Message message);

    Page<Message> getMessage(ActivityType activityType, Long activityId, Pageable pageable);

    void updateMessage(Long messageId, Message message);

    void deleteMessage(Long messageId, ActivityType activityType);
}
