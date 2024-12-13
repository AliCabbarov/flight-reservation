package ingress.flightms.model.entity;

import ingress.common.model.entity.AbstractEntity;
import ingress.flightms.model.enums.PlaceStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SQLRestriction("status = true")
@SuperBuilder
public class FlightPlanePlace extends AbstractEntity {
    @ManyToOne
    private Flight flight;
    @ManyToOne
    private PlanePlace planePlace;
    @ManyToOne
    private Ticket ticket;
    @Enumerated(EnumType.STRING)
    private PlaceStatus placeStatus;
}
