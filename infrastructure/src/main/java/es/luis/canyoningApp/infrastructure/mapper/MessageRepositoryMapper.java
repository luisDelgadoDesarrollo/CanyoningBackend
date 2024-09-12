package es.luis.canyoningApp.infrastructure.mapper;

import es.luis.canyoningApp.domain.model.Message;
import es.luis.canyoningApp.infrastructure.entity.CanyonMessageEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface MessageRepositoryMapper {

  @Mapping(target = "placeId", source = "canyon.canyonId")
  @Mapping(target = "messageId", source = "canyonMessageId")
  @Mapping(
      target = "typePlace",
      expression = "java(es.luis.canyoningApp.domain.model.ActivityType.CANYON)")
  @Mapping(target = "date", source = "sendAt")
  Message canyonMessageEntityToMessage(CanyonMessageEntity canyonMessageEntity);

  @Mapping(target = "canyon.canyonId", source = "placeId")
  @Mapping(target = "canyonMessageId", source = "messageId")
  CanyonMessageEntity messageToCanyonMessageEntity(Message message);
}
