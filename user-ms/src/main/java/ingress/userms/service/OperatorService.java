package ingress.userms.service;

import ingress.common.model.kafka.OperatorRegisterDto;
import ingress.userms.model.dto.request.OperatorRequestDto;
import ingress.userms.model.entity.User;
import jakarta.servlet.http.HttpServletResponse;

public interface OperatorService {

    void registerOperator(OperatorRequestDto dto);

    void removeOperatorRole(OperatorRequestDto dto);

    void approvalOperator(OperatorRequestDto dto);

}
