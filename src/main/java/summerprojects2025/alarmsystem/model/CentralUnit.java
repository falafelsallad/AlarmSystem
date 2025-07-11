package summerprojects2025.alarmsystem.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class CentralUnit {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String serial;

    // Status for the Central Unit, perhaps on/off/ready
    private String status;

    @ManyToOne
    private Customer customer;

    @OneToMany(mappedBy = "centralUnit")
    private List<Tag> tags;

    @ManyToMany(mappedBy = "centralUnits")
    private List<User> users;


}
