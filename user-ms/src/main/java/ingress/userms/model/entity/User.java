package ingress.userms.model.entity;

import ingress.common.model.entity.AbstractEntity;
import ingress.common.model.constant.Roles;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity(name = "_user")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public class User extends AbstractEntity {
    private String email;
    private String password;
    private Boolean isActive;
    private Boolean isEnabled;
    @Enumerated(EnumType.STRING)
    private Roles role;
    private String firstName;
    private String lastName;
}
