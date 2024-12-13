package company.notificationms.service.listener.impl;

import company.notificationms.mapper.NotificationMapper;
import company.notificationms.model.dto.NotificationResponseDto;
import company.notificationms.model.entity.Notification;
import company.notificationms.model.enums.NotificationState;
import company.notificationms.repository.NotificationRepository;
import company.notificationms.service.listener.NotificationService;
import ingress.common.model.exception.ApplicationException;
import lombok.RequiredArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static company.notificationms.model.enums.Exceptions.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final NotificationMapper notificationMapper;

    @Override
    public List<NotificationResponseDto> getNotificationsByState(String state) {

        List<Notification> notifications = notificationRepository.findAllByState(NotificationState.valueOf(state.toUpperCase())).orElseThrow(() -> new ApplicationException(NOT_FOUND));

        notifications.forEach(notification -> notification.setSentAt(LocalDateTime.now()));

        List<NotificationResponseDto> list = notifications.stream()
                .map(notificationMapper::notificationToResponseDto)
                .toList();

        notificationRepository.saveAll(notifications);

        return list;
    }

    @Override
    public void markAsRead(List<Long> ids) {

        List<Notification> notifications = notificationRepository.findAllByIdIn(ids).orElseThrow(() -> new ApplicationException(NOT_FOUND));

        notifications.forEach(notification -> {
            notification.setState(NotificationState.READ);
        });
        notificationRepository.saveAll(notifications);
    }
}
