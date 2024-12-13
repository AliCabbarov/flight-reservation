package company.notificationms.service.listener;

import ingress.common.model.kafka.OperatorRegisterDto;

public interface OperatorRegisterListenerService {
    void sendNotificationEmail(OperatorRegisterDto operatorRegisterDto);

    void sendNotificationApp(OperatorRegisterDto operatorRegisterDto);
}
