package summerprojects2025.alarmsystem.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String pinHash;

    //change to central unit
    @ManyToOne
    private Customer customer;

    @ManyToMany
    private Set<CentralUnit> centralUnits;

    // One tag per profile
    @OneToOne(mappedBy = "user")
    private Tag tag;

}
