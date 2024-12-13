package ingress.common.model.kafka;

public record OperatorNotificationDto(
        Long flightId,
        String operatorEmail,
        String operatorName,
        String operatorSurname,
        String approvalState,
        String subject,
        String feedbackMessage) {
}