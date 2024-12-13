package company.notificationms.service.listener;


import ingress.common.model.kafka.UserResetPasswordDto;

public interface PasswordResetEmailService {
    void sendEmail(UserResetPasswordDto dto);
}
