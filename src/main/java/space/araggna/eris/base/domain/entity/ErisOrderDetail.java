package space.araggna.eris.base.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "erisorderdetail")
public class ErisOrderDetail extends AbstractEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderid")
    private ErisOrder orderId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "skuid")
    private space.araggna.eris.base.domain.entity.ErisSku skuId;

    @NotNull
    @Column(name = "orderqty", nullable = false)
    private Integer orderQty;

    @NotNull
    @Column(name = "ordersubtotal", nullable = false)
    private BigDecimal orderSubTotal;

}