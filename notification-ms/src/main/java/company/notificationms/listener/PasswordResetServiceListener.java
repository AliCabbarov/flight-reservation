package company.notificationms.listener;

import company.notificationms.service.listener.PasswordResetEmailService;
import company.notificationms.service.listener.UserRegisterListenerService;
import ingress.common.model.kafka.UserRegisterDto;
import ingress.common.model.kafka.UserResetPasswordDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PasswordResetServiceListener {
    private final PasswordResetEmailService passwordResetEmailService;
    @RetryableTopic(
            attempts = "3",
            autoCreateTopics = "true",
            backoff = @Backoff(delay = 1000L, multiplier = 3),
            dltTopicSuffix = "MY_DLT",
            retryTopicSuffix = "MY_RETRY",
            autoStartDltHandler = "true"
    )
    @KafkaListener(topics = "reset-password", groupId = "reset-password-notification",containerFactory = "kafkaListenerContainerFactoryForResetPassword")
    public void listenResetPassword(UserResetPasswordDto dto){
        log.info("Reset Password event received: {}", dto);
        passwordResetEmailService.sendEmail(dto);
    }
}
