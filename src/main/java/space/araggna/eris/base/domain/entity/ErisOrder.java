package space.araggna.eris.base.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "erisorder")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "orderid", nullable = false))
})
public class ErisOrder extends AbstractEntity {
    @Size(max = 10)
    @NotNull
    @Column(name = "ordercode", nullable = false, length = 10)
    private String orderCode;

    @NotNull
    @Column(name = "ordertotal", nullable = false)
    private BigDecimal orderTotal;

    @Column(name = "orderstatus", columnDefinition = "order_status not null")
    private OrderStatus orderStatus;
}