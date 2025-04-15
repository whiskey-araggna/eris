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
@Table(name = "erissku")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "skuid", nullable = false))
})
public class ErisSku extends AbstractEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productid")
    private ErisProduct productId;

    @Size(max = 20)
    @NotNull
    @Column(name = "skucode", nullable = false, length = 20)
    private String skuCode;

    @Size(max = 100)
    @Column(name = "skuname", length = 100)
    private String skuName;

    @NotNull
    @Column(name = "skuprice", nullable = false)
    private BigDecimal skuPrice;

}