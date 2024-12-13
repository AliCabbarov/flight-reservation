package company.notificationms.mapper;

import company.notificationms.model.dto.NotificationResponseDto;
import company.notificationms.model.entity.Notification;
import ingress.common.model.kafka.OperatorRegisterDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NotificationMapper {
    NotificationResponseDto notificationToResponseDto(Notification notification);
    Notification dtoToEntity(OperatorRegisterDto dto);
}
