package ingress.flightms.repository;

import ingress.flightms.model.entity.Airline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AirlineRepository extends JpaRepository<Airline, Long> {
    Optional<Airline> findById(long id);
    Optional<Airline> findByName(String name);
}
