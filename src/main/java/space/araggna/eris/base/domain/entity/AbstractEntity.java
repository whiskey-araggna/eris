package space.araggna.eris.base.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UuidGenerator;
import org.jspecify.annotations.Nullable;
import org.springframework.data.util.ProxyUtils;

import java.time.LocalDateTime;
import java.util.UUID;

@MappedSuperclass
@Getter
@Setter
public abstract class AbstractEntity {

    @Id
    @UuidGenerator
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "createdby")
    private UUID createdBy;

    @Column(name = "createddate")
    private LocalDateTime createdDate;

    @Column(name = "updatedby")
    private UUID updatedBy;

    @Column(name = "updateddate")
    private LocalDateTime updatedDate;

    @Column(name = "isactive")
    private Boolean isActive;

}
