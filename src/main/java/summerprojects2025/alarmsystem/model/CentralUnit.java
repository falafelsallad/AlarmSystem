package summerprojects2025.alarmsystem.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class CentralUnit {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String serial;

    // Status for the Central Unit, perhaps on/off/ready
    private boolean status;

    @ManyToOne
    private Customer customer;

    //TODO: once you get a user you  get  a pin code. But one Central unit must not have to have a tag.

    // can be connected to more than one profiles,
    @ManyToMany(mappedBy = "centralUnits")
    private List<User> users;

    @ManyToMany(mappedBy = "centralUnits")
    private Set<Tag> tags;




}
