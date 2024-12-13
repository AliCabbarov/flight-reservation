package ingress.common.model.kafka;

public record UserResetPasswordDto(String email, String firstName, String lastName, String token) {
}
