package company.notificationms.service.listener.impl;

import company.notificationms.model.entity.Notification;
import company.notificationms.model.enums.NotificationState;
import company.notificationms.repository.NotificationRepository;
import company.notificationms.service.MailService;
import company.notificationms.service.listener.OperatorRegisterListenerService;
import ingress.common.model.kafka.OperatorRegisterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class OperatorRegisterListenerServiceImpl implements OperatorRegisterListenerService {

    private final MailService mailService;
    private final NotificationRepository notificationRepository;

    private static final String SUBJECT = "User register for Operator";

    @Override
    public void sendNotificationEmail(OperatorRegisterDto operatorRegisterDto) {
        String link = "http://localhost:8083/api/v1/operators/admin/operator/approval";
        String message = "Dear Admin,\n\n" +
                "A new operator registration request has been made. Below are the details of the operator:\n\n" +
                "First Name: " + operatorRegisterDto.firstName() + "\n" +
                "Last Name: " + operatorRegisterDto.lastName() + "\n" +
                "Email: " + operatorRegisterDto.email() + "\n\n" +
                "To approve this request and assign the operator role, please click the link below:\n\n" +
                link + "\n\n" +
                "If you did not initiate this request, please disregard this email.\n\n" +
                "Best regards,\n" +
                "The Flight Application Team";
        operatorRegisterDto.adminEmails().forEach(adminEmail -> mailService.sendMail( adminEmail, SUBJECT, message));
    }

    @Override
    public void sendNotificationApp(OperatorRegisterDto operatorRegisterDto) {

        String notificationMessage = "A new operator registration request has been made.\n\n" +
                "Details of the operator:\n" +
                "First Name: " + operatorRegisterDto.firstName() + "\n" +
                "Last Name: " + operatorRegisterDto.lastName() + "\n" +
                "Email: " + operatorRegisterDto.email() + "\n\n" +
                "Please review and approve the request if necessary.";


        Notification notification = Notification.builder()
                .message(notificationMessage)
                .state(NotificationState.UNREAD)
                .createDate(LocalDateTime.now())
                .build();

        notificationRepository.save(notification);
    }

}
