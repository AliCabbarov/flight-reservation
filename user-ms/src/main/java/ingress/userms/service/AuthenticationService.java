package ingress.userms.service;

import ingress.userms.model.dto.request.LoginRequestDto;
import ingress.userms.model.dto.request.UserRequestDto;
import ingress.userms.model.dto.response.TokenResponseDto;

public interface AuthenticationService {
    void createUser(UserRequestDto userRequestDto);

    void verifyUser(String token);

    TokenResponseDto login(LoginRequestDto dto);

    TokenResponseDto refresh(String refreshToken);
}
