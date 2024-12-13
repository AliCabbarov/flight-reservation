package ingress.common.model.entity;

import ingress.common.config.listener.AbstractEntityListener;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
@Getter
@Setter
@MappedSuperclass
@EntityListeners(AbstractEntityListener.class)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "entity_seq")
    @SequenceGenerator(name = "entity_seq", sequenceName = "entity_seq", allocationSize = 1)
    protected Long id;
    @Column(updatable = false, nullable = false)
    protected LocalDateTime createdDate;
    @Column(insertable = false)
    protected LocalDateTime updatedDate;
    @Column(updatable = false, nullable = false)
    protected Long createdBy;
    @Column(insertable = false)
    protected Long updatedBy;
    protected Boolean status;
}
