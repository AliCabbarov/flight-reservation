package ingress.flightms.repository;

import ingress.flightms.model.entity.Flight;
import ingress.flightms.model.entity.Ticket;
import ingress.flightms.model.entity.TicketRequest;
import ingress.flightms.model.enums.TicketStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    boolean existsByTicketRequest(TicketRequest ticketRequest);

    Optional<Ticket> findByIdAndStatusAndTicketStatus(Long id, Boolean status, TicketStatus ticketStatus);

    List<Ticket> findByStatusAndFlightAndTicketStatus(Boolean status, Flight flight, TicketStatus ticketStatus);
}