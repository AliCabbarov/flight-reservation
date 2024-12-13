package company.notificationms.service.listener;

import ingress.common.model.kafka.AdminNotificationDto;

public interface AdminEmailService {
    void sendNotification(AdminNotificationDto adminNotificationDto);
}
