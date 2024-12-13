package company.notificationms.service.listener.impl;

import company.notificationms.service.MailService;
import ingress.common.model.dto.TicketMailDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TicketCreateService {
    private final MailService mailService;
    public void sendNotification(TicketMailDto dto) {
        mailService.sendMail(dto.getEmail(), dto.getSubject(), dto.getTicketContent());
    }
}
