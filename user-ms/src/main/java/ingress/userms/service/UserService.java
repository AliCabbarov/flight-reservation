package ingress.userms.service;

import ingress.userms.model.dto.response.UserResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    UserResponseDto getUserDetailsById(Long id);

    void deleteById(Long id);

    List<UserResponseDto> getAll();
}
