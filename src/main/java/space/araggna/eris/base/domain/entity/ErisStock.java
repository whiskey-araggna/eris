package space.araggna.eris.base.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "erisstock")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "stockid", nullable = false))
})
public class ErisStock extends AbstractEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "skuid")
    private ErisSku skuId;

    @ColumnDefault("0")
    @Column(name = "stockavailable")
    private Integer stockAvailable;

    @ColumnDefault("0")
    @Column(name = "stockhold")
    private Integer stockHold;

}