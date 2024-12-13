package ingress.flightms.service;

import ingress.flightms.model.dto.request.TicketConfirmationRequestDto;
import ingress.flightms.model.dto.request.TicketCreateRequestDto;
import ingress.flightms.model.dto.request.TicketCreateResponseDto;
import ingress.flightms.model.dto.request.TicketRequestDto;
import ingress.flightms.model.dto.response.TicketConfirmationResponseDto;
import ingress.flightms.model.dto.response.TicketResponseDto;
import org.springframework.http.ResponseEntity;

public interface TicketService {
    TicketResponseDto createTicketRequest(TicketRequestDto ticketRequestDto);

    TicketCreateResponseDto createTicket(TicketCreateRequestDto ticketCreateRequestDto);

    TicketConfirmationResponseDto confirm(TicketConfirmationRequestDto dto);

    void refundTicket(Long ticketId);
}
