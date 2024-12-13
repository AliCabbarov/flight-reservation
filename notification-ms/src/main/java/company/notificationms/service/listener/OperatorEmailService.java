package company.notificationms.service.listener;

import ingress.common.model.kafka.OperatorNotificationDto;

public interface OperatorEmailService {
    void sendNotification(OperatorNotificationDto operatorNotificationDto);
}
