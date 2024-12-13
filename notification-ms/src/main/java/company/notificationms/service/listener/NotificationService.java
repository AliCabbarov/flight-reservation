package company.notificationms.service.listener;


import company.notificationms.model.dto.NotificationResponseDto;

import java.util.List;

public interface NotificationService {

    List<NotificationResponseDto> getNotificationsByState(String state);

    void markAsRead(List<Long> ids);

}
