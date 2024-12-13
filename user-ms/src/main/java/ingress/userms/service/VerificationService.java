package ingress.userms.service;

import ingress.userms.model.entity.User;
import ingress.userms.model.enums.TokenType;

public interface VerificationService {
    String sendVerificationEmail(User user);


    User getUserByValidToken(String token, TokenType type);
}
