package space.araggna.eris.base.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "erisuser")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "userid", nullable = false))
})
public class ErisUser extends AbstractEntity {
    @Size(max = 150)
    @NotNull
    @Column(name = "useremail", nullable = false, length = 150)
    private String userEmail;

    @Size(max = 100)
    @NotNull
    @Column(name = "userfullname", nullable = false, length = 100)
    private String userFullName;

    @Size(max = 150)
    @NotNull
    @Column(name = "userpassword", nullable = false, length = 150)
    private String userPassword;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "erisuser_roles", joinColumns = @JoinColumn(name = "userid"))
    @Column(name = "userrole", nullable = false)
    @Enumerated(EnumType.STRING)
    private Set<UserRole> userRole;
}