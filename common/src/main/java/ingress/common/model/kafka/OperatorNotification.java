package ingress.common.model.kafka;

public record OperatorNotification(Long flightId, String approvalState, String comments) {}