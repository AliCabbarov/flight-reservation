package ingress.userms.controller;

import ingress.userms.model.dto.ForgetPasswordDto;
import ingress.userms.model.dto.request.LoginRequestDto;
import ingress.userms.model.dto.request.UserRequestDto;
import ingress.userms.model.dto.response.TokenResponseDto;
import ingress.userms.service.AuthenticationService;
import ingress.userms.service.PasswordService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final PasswordService passwordService;
    @PostMapping("/register")
    public ResponseEntity<Void> createUser(@RequestBody @Valid UserRequestDto userRequestDto) {
        authenticationService.createUser(userRequestDto);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/verify")
    public ResponseEntity<Void> verifyUser(@RequestParam String token) {
        authenticationService.verifyUser(token);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> login(@RequestBody @Valid LoginRequestDto dto){
        return ResponseEntity.ok(authenticationService.login(dto));
    }
    @GetMapping("/refresh")
    public ResponseEntity<TokenResponseDto> refresh(@RequestParam String refreshToken){
        return ResponseEntity.ok(authenticationService.refresh(refreshToken));
    }

    @PostMapping("/forget-password")
    public ResponseEntity<Void> forgetPassword(@RequestBody ForgetPasswordDto forgetPasswordDto) {
        passwordService.forgetPassword(forgetPasswordDto.getEmail());
        return ResponseEntity.ok().build();
    }
}