package company.notificationms.service.listener;

import ingress.common.model.kafka.UserRegisterDto;

public interface UserRegisterListenerService {
    void sendNotification(UserRegisterDto dto);
}
