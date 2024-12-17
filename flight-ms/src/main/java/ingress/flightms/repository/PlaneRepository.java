package ingress.flightms.repository;

import ingress.flightms.model.entity.Plane;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PlaneRepository extends JpaRepository<Plane, Long> {
    @Query("SELECT p FROM Plane p LEFT JOIN FETCH p.planePlaces WHERE p.id = :id")
    Optional<Plane> findByIdWithPlanePlaces(@Param("id") Long id);

    List<Plane> findByStatus(Boolean status);

    Optional<Plane> findByIdAndStatus(Long id, Boolean status);
}
