package ingress.common.model.kafka;

public record UserRegisterDto(String email, String firstName, String lastName, String token) {
}
