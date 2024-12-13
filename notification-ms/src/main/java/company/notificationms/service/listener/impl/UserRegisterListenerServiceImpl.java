package company.notificationms.service.listener.impl;

import company.notificationms.service.MailService;
import company.notificationms.service.listener.UserRegisterListenerService;
import ingress.common.model.kafka.UserRegisterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserRegisterListenerServiceImpl implements UserRegisterListenerService {
    private final MailService mailService;
    @Value("${application.user-ms.server}")
    private String USER_MS_SERVER;
    @Value("${application.front.server}")
    private String FRONTEND_SERVER;

    @Override
    public void sendNotification(UserRegisterDto dto) {
        String link = "http://" + FRONTEND_SERVER + "/flight/verify/index.html?token=" + dto.token();
        String message = "Welcome to our platform. Thank you for registering " + dto.firstName() + " " + dto.lastName() + ". Please verify your email. Please click the link below\n " + link;
        mailService.sendMail(dto.email(), "flight application register service", message);
    }
}
