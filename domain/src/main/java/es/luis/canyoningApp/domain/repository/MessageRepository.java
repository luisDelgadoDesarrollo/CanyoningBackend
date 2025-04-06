package es.luis.canyoningApp.domain.repository;

import es.luis.canyoningApp.domain.model.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MessageRepository {
    void postCanyonMessage(Message message);

    Page<Message> getCanyonMessages(Long activityId, Pageable pageable);

    Message getCanyonMessage(Long messageId);

    void updateMessage(Message canyonMessage);

    void deleteCanyonMessage(Long messageId);
}
