package es.luis.canyoningApp.application.rest.mapper;

import es.luis.canyoningApp.canyoningApp.application.rest.model.MessageInDto;
import es.luis.canyoningApp.canyoningApp.application.rest.model.MessageOutDto;
import es.luis.canyoningApp.domain.model.Message;
import org.mapstruct.Mapper;

@Mapper
public interface MessageControllerMapper extends BaseControllerMapper {
  Message messageInDtoToMessage(MessageInDto messageInDto);

  MessageOutDto messageToMessageOutDto(Message message);
}
