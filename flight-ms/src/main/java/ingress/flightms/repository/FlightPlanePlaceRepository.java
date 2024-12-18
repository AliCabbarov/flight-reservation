package ingress.flightms.repository;

import ingress.flightms.model.entity.Flight;
import ingress.flightms.model.entity.FlightPlanePlace;
import ingress.flightms.model.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;

import java.util.List;
import java.util.Optional;

public interface FlightPlanePlaceRepository extends JpaRepository<FlightPlanePlace, Long> {
    boolean existsByFlightIdAndPlanePlaceIdAndStatus(Long flightId, Long planePlaceId,Boolean status);
    @Query(value = """
            select p.planePlace.id
            from FlightPlanePlace p
            where p.flight.id = ?1
            and p.status = true
            """)
    List<Integer> findPlaceNumberByFlightId(Long flightId);

    Optional<FlightPlanePlace> findByStatusAndTicket(Boolean status, Ticket ticket);

    List<FlightPlanePlace> findByStatusAndFlight(Boolean status, Flight flight);
}
