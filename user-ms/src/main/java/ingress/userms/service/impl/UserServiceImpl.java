package ingress.userms.service.impl;

import ingress.common.model.exception.ApplicationException;
import ingress.userms.model.dto.response.UserResponseDto;
import ingress.userms.model.entity.User;
import ingress.userms.repository.UserRepository;
import ingress.userms.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static ingress.userms.model.enums.Exceptions.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserResponseDto getUserDetailsById(Long id) {
        User user = getUser(id);
        return new UserResponseDto(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail());
    }

    private User getUser(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ApplicationException(NOT_FOUND, id));
    }

    @Override
    public void deleteById(Long id) {
        User user = getUser(id);
        user.setStatus(false);
        user.setIsEnabled(false);
        userRepository.save(user);
    }

    @Override
    public List<UserResponseDto> getAll() {
        List<User> users = userRepository.findByStatusAndIsEnabled(true, true);
        return users.stream()
                .map(user -> new UserResponseDto(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail()))
                .toList();
    }
}
