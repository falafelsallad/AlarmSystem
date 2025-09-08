package summerprojects2025.alarmsystem.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Set;

@Entity
@Getter
@Setter
@Table (name = "app_user")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Column(name = "pinHash")
    private String pinHash;

    private Integer avatar;

    @ManyToOne
    private Customer customer;

    @ManyToMany
    @JoinTable(
            name = "app_user_central_unit",
            joinColumns = @JoinColumn(name = "app_user_id"),
            inverseJoinColumns = @JoinColumn(name = "central_unit_id")
    )
    private Set<CentralUnit> centralUnits;
}
