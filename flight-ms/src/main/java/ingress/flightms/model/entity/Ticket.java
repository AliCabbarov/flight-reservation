package ingress.flightms.model.entity;

import ingress.common.model.entity.AbstractEntity;
import ingress.flightms.model.enums.TicketStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Ticket extends AbstractEntity {
    private String ticketNo;
    private String passengerName;
    private String passengerSurname;
    private String email;
    private String phone;
    private Long boughtUserId;
    @Enumerated(EnumType.STRING)
    private TicketStatus ticketStatus;
    @ManyToOne
    private Flight flight;
    @OneToOne
    private TicketRequest ticketRequest;
}
