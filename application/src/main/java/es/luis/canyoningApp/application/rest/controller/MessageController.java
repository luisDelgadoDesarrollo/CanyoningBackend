package es.luis.canyoningApp.application.rest.controller;

import es.luis.canyoningApp.application.rest.mapper.MessageControllerMapper;
import es.luis.canyoningApp.canyoningApp_application.rest.api.MessageApi;
import es.luis.canyoningApp.canyoningApp_application.rest.model.ActivityTypeDto;
import es.luis.canyoningApp.canyoningApp_application.rest.model.MessageInDto;
import es.luis.canyoningApp.canyoningApp_application.rest.model.MessageOutDto;
import es.luis.canyoningApp.domain.model.Message;
import es.luis.canyoningApp.domain.service.MessageService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
public class MessageController extends BaseController implements MessageApi {

  @Autowired private MessageService messageService;

  @Autowired private MessageControllerMapper messageControllerMapper;

  @Override
  public ResponseEntity<Void> deleteMessage(Long messageId, ActivityTypeDto activityTypeDto) {
    messageService.deleteMessage(
        messageId, messageControllerMapper.activityTypeDtoToActivityType(activityTypeDto));
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  @Override
  public ResponseEntity<List<MessageOutDto>> getMessages(
      ActivityTypeDto activityType,
      Long activityId,
      Integer page,
      Integer size,
      String sort,
      Pageable pageable) {
    // todo fix this pageable i dont like it
    Page<Message> messages =
        messageService.getMessage(
            messageControllerMapper.activityTypeDtoToActivityType(activityType),
            activityId,
            pageable);
    addPaginationHeadersToResponse(messages);
    return ResponseEntity.ok(
        messages.getContent().stream()
            .map(messageControllerMapper::messageToMessageOutDto)
            .toList());
  }

  @Override
  public ResponseEntity<Void> postMessage(MessageInDto messageDto) {
    messageService.postMessage(messageControllerMapper.messageInDtoToMessage(messageDto));
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  @Override
  public ResponseEntity<Void> updateMessage(Long messageId, MessageInDto messageInDto) {
    messageService.updateMessage(
        messageId, messageControllerMapper.messageInDtoToMessage(messageInDto));
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}
