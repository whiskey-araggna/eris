package space.araggna.eris.base.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "eriscategory")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "categoryid", nullable = false))
})
public class ErisCategory extends AbstractEntity {
    @Size(max = 10)
    @NotNull
    @Column(name = "categorycode", nullable = false, length = 10)
    private String categoryCode;

    @Size(max = 100)
    @NotNull
    @Column(name = "categoryname", nullable = false, length = 100)
    private String categoryName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoryreff")
    private ErisCategory categoryReff;

}