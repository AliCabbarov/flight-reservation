package ingress.userms.service;

import ingress.userms.model.dto.response.UserResponseDto;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    UserResponseDto getUserDetailsById(Long id);

}
