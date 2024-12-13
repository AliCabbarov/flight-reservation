package ingress.flightms.controller;

import ingress.flightms.model.dto.request.TicketConfirmationRequestDto;
import ingress.flightms.model.dto.request.TicketCreateRequestDto;
import ingress.flightms.model.dto.request.TicketCreateResponseDto;
import ingress.flightms.model.dto.request.TicketRequestDto;
import ingress.flightms.model.dto.response.TicketConfirmationResponseDto;
import ingress.flightms.model.dto.response.TicketResponseDto;
import ingress.flightms.service.TicketService;
import ingress.flightms.service.impl.FileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/tickets")
@RequiredArgsConstructor
public class TicketController {
    private final TicketService ticketService;

    @PostMapping("/request")
    public ResponseEntity<TicketResponseDto> createTicketRequest(@RequestBody TicketRequestDto ticketRequestDto) {
        return ResponseEntity.ok(ticketService.createTicketRequest(ticketRequestDto));
    }

    @PostMapping("/create")
    public ResponseEntity<TicketCreateResponseDto> createResponseDtoResponseEntity(@RequestBody @Valid TicketCreateRequestDto ticketCreateRequestDto) {
        return ResponseEntity.ok(ticketService.createTicket(ticketCreateRequestDto));
    }
    @PostMapping("/confirm")
    public ResponseEntity<TicketConfirmationResponseDto> confirm(@RequestBody TicketConfirmationRequestDto dto){
        return ResponseEntity.ok(ticketService.confirm(dto));
    }
    @GetMapping("refund/{ticketId}")
    public ResponseEntity<Void> refundTicket(@PathVariable Long ticketId){
        ticketService.refundTicket(ticketId);
        return ResponseEntity.ok().build();
    }
}
