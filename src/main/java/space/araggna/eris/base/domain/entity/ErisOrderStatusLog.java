package space.araggna.eris.base.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "erisorderstatuslog")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "statuslogid", nullable = false))
})
public class ErisOrderStatusLog extends AbstractEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderid")
    private ErisOrder orderId;

    @Column(name = "statusnote", length = Integer.MAX_VALUE)
    private String statusNote;

    @Column(name = "orderstatus", columnDefinition = "order_status not null")
    private OrderStatus orderStatus;
}