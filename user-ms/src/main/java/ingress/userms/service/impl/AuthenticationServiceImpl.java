package ingress.userms.service.impl;

import ingress.common.model.exception.ApplicationException;
import ingress.common.model.kafka.UserRegisterDto;
import ingress.common.service.JwtService;
import ingress.userms.mapper.UserMapper;
import ingress.userms.model.dto.request.LoginRequestDto;
import ingress.userms.model.dto.request.UserRequestDto;
import ingress.userms.model.dto.response.TokenResponseDto;
import ingress.userms.model.entity.User;
import ingress.userms.model.enums.TokenType;
import ingress.userms.producer.KafkaProducer;
import ingress.userms.repository.UserRepository;
import ingress.userms.service.AuthenticationService;
import ingress.userms.service.VerificationService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

import static ingress.userms.model.enums.Exceptions.*;

@Service
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final VerificationService verificationService;
    private final KafkaProducer kafkaProducer;
    private final JwtService jwtService;
    private final HttpServletResponse response;
    @Value("${kafka.topic.user-registration}")
    private String USER_REGISTRATION_TOPIC;

    public AuthenticationServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, UserMapper userMapper, VerificationService verificationService, KafkaProducer kafkaProducer, JwtService jwtService, HttpServletResponse response) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
        this.verificationService = verificationService;
        this.kafkaProducer = kafkaProducer;
        this.jwtService = jwtService;
        this.response = response;
    }

    @Override
    public void createUser(UserRequestDto userRequestDto) {
        userRepository.findByStatusAndEmailAndIsActiveAndIsEnabled(true, userRequestDto.email(), true, true)
                .ifPresent(u -> {
                    throw new ApplicationException(USERNAME_ALREADY_EXISTS);
                });
        User user = userMapper.map(userRequestDto);
        user.setPassword(passwordEncoder.encode(userRequestDto.password()));
        userRepository.save(user);
        String registrationToken = verificationService.sendVerificationEmail(user);
        kafkaProducer.sendUserRegistration(USER_REGISTRATION_TOPIC, new UserRegisterDto(user.getEmail(), user.getFirstName(), user.getLastName(), registrationToken));
    }

    @Override
    public void verifyUser(String token) {
        User user = verificationService.getUserByValidToken(token, TokenType.REGISTRATION);
        user.setIsEnabled(true);
        userRepository.save(user);
    }

    @Override
    public TokenResponseDto login(LoginRequestDto dto) {
        User user;
        try {
            user = getUserByUsername(dto.username());
        } catch (ApplicationException e) {
            throw new ApplicationException(BAD_CREDENTIALS);
        }

        if (!passwordEncoder.matches(dto.password(), user.getPassword())) {
            throw new ApplicationException(BAD_CREDENTIALS);
        }

        String access = jwtService.generateToken(user.getEmail(), generateClaims(user));
        String refresh = jwtService.generateRefresh(user.getEmail(), generateRefreshClaims(user));
        return new TokenResponseDto(access, refresh);
    }

    @Override
    public TokenResponseDto refresh(String refreshToken) {
        String username = jwtService.getUsername(refreshToken, response);
        User user = getUserByUsername(username);

        String access = jwtService.generateToken(user.getEmail(), generateClaims(user));
        String refresh = jwtService.generateRefresh(user.getEmail(), generateRefreshClaims(user));
        return new TokenResponseDto(access, refresh);
    }

    private User getUserByUsername(String username) {
        return userRepository.findByStatusAndEmailAndIsActiveAndIsEnabled(true, username, true, true)
                .orElseThrow(() -> new ApplicationException(NOT_FOUND, username));
    }

    private Map<String, Object> generateClaims(User user) {
        return Map.of("role", user.getRole().name(), "username", user.getEmail(), "userId", user.getId());
    }

    private Map<String, Object> generateRefreshClaims(User user) {
        return Map.of("username", user.getEmail(), "userId", user.getId());
    }
}
//test deploy