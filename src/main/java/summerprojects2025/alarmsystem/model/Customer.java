package summerprojects2025.alarmsystem.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class Customer {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String ssn;
    private String address;
    private String email; // Username

    @Column(name = "phone")
    private String phoneNumber;

    @Column(name = "passwordhash")
    private String passwordHash;

    @OneToMany(mappedBy = "customer")
    private Set<CentralUnit> centralUnits;

    @OneToMany(mappedBy = "customer")
    private Set<User> users;

}
