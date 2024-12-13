package ingress.userms.service.impl;

import ingress.common.model.exception.ApplicationException;
import ingress.common.model.kafka.OperatorRegisterDto;
import ingress.userms.model.dto.request.OperatorRequestDto;
import ingress.userms.model.entity.User;
import ingress.common.model.constant.Roles;
import ingress.userms.producer.KafkaProducer;
import ingress.userms.repository.UserRepository;
import ingress.userms.service.OperatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

import static ingress.userms.model.enums.Exceptions.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class OperatorServiceImpl implements OperatorService {

    private final UserRepository userRepository;
    private final KafkaProducer kafkaProducer;

    @Value("${kafka.topic.operator-register}")
    private String OPERATOR_REGISTER_TOPIC;

    @Override
    public void registerOperator(OperatorRequestDto dto) {

        User user = userRepository.findByEmail(dto.email()).orElseThrow(() -> new ApplicationException(NOT_FOUND));
        List<String> adminEmails = userRepository.findEmailsByRole(Roles.ADMIN).orElseThrow(() -> new ApplicationException(NOT_FOUND));
        userRepository.save(user);
        kafkaProducer.sendOperatorRegisterMessage(OPERATOR_REGISTER_TOPIC, new OperatorRegisterDto(user.getEmail(), user.getFirstName(), user.getLastName(), adminEmails));
    }

    @Override
    public void approvalOperator(OperatorRequestDto dto) {

        User user = userRepository.findByEmail(dto.email()).orElseThrow(() -> new ApplicationException(NOT_FOUND));
        user.setRole(Roles.OPERATOR);
        userRepository.save(user);
    }

    @Override
    public void removeOperatorRole(OperatorRequestDto dto) {

        User user = userRepository.findByEmail(dto.email()).orElseThrow(() -> new ApplicationException(NOT_FOUND));
        user.setRole(Roles.CUSTOMER);
        userRepository.save(user);
    }
}
