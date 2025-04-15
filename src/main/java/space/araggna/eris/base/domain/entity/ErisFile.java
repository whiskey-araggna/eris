package space.araggna.eris.base.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "erisfile")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "fileid", nullable = false))
})
public class ErisFile extends AbstractEntity {

    @NotNull
    @Column(name = "filereff", nullable = false)
    private UUID fileReff;

    @Size(max = 200)
    @NotNull
    @Column(name = "filepath", nullable = false, length = 200)
    private String filePath;
    @ColumnDefault("true")
    @Column(name = "isactive")
    private Boolean isActive;

    @Column(name = "filecategory", columnDefinition = "file_category not null")
    private FileCategory fileCategory;
}