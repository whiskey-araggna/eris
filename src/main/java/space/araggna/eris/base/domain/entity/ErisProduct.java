package space.araggna.eris.base.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "erisproduct")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "productid", nullable = false))
})
public class ErisProduct extends AbstractEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoryid")
    private ErisCategory categoryId;

    @Size(max = 10)
    @NotNull
    @Column(name = "productcode", nullable = false, length = 10)
    private String productCode;

    @Size(max = 100)
    @NotNull
    @Column(name = "productname", nullable = false, length = 100)
    private String productName;

    @Column(name = "productdesc", length = Integer.MAX_VALUE)
    private String productDesc;

}