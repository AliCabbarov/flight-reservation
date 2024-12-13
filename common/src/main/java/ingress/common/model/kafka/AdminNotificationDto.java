package ingress.common.model.kafka;

public record AdminNotificationDto(
        Long operatorId,
        Long flightId,
        String subject) {
}
