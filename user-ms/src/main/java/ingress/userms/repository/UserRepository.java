package ingress.userms.repository;

import ingress.userms.model.entity.User;
import ingress.common.model.constant.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Optional<User> findByStatusAndEmailAndIsActiveAndIsEnabled(Boolean status, String email, Boolean isActive, Boolean isEnabled);

    @Query("select u.email from _user as u where u.role = :role")
    Optional<List<String>> findEmailsByRole(@Param("role") Roles role);

    Optional<User> findByEmailAndIsEnabledAndStatusAndIsActive(String email, Boolean isEnabled, Boolean status, Boolean isActive);

    List<User> findByStatusAndIsEnabled(Boolean status, Boolean isEnabled);
}