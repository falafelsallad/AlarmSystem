package summerprojects2025.alarmsystem.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

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
    private String passwordHash; // Bcrypt password get 60 characters long no matter the original password length.

    @OneToMany(mappedBy = "customer")
    private List<CentralUnit> centralUnits;

    @OneToMany(mappedBy = "customer")
    private List<User> users;

}
