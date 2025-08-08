package summerprojects2025.alarmsystem.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.web.embedded.netty.NettyWebServer;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "central_unit")
public class CentralUnit {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "serial_number")
    private String serial;

    // Status for the Central Unit, perhaps on/off/ready
    private boolean status;

    private String name;

    @ManyToOne
    private Customer customer;

    //TODO: once you get a user you  get  a pin code. But one Central unit must not have to have a tag.

    // can be connected to more than one profiles,
    @ManyToMany(mappedBy = "centralUnits")
    private Set<Tag> tags = new HashSet<>();

    @ManyToMany(mappedBy = "centralUnits")
    private Set<User> users = new HashSet<>();






}
