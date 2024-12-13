package ingress.userms.service.impl;

import ingress.common.model.exception.ApplicationException;
import ingress.userms.model.dto.response.UserResponseDto;
import ingress.userms.model.entity.User;
import ingress.userms.repository.UserRepository;
import ingress.userms.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static ingress.userms.model.enums.Exceptions.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserResponseDto getUserDetailsById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ApplicationException(NOT_FOUND, id));
        return new UserResponseDto(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail());
    }
}
