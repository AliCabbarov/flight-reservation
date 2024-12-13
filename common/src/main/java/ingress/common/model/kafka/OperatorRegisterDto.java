package ingress.common.model.kafka;

import java.util.List;

public record OperatorRegisterDto(String email, String firstName, String lastName, List<String> adminEmails) {
}
