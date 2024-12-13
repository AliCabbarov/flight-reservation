package ingress.userms.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

public record LoginRequestDto(@NotBlank String username, @NotBlank String password) {}
