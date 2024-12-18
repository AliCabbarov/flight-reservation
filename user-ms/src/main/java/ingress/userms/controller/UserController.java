package ingress.userms.controller;

import ingress.userms.model.dto.response.UserResponseDto;
import ingress.userms.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/info/{id}")
    public ResponseEntity<UserResponseDto> getUserDetailsById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserDetailsById(id));
    }
}
