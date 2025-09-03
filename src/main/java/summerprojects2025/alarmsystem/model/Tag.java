package summerprojects2025.alarmsystem.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class Tag {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tag_id_hash")
    private String tagIdHash;


    @ManyToMany
    @JoinTable(
            name = "central_unit_tag",
            joinColumns = @JoinColumn(name = "tag_id"),
            inverseJoinColumns = @JoinColumn(name = "central_unit_id")
    )
    private Set<CentralUnit> centralUnits;

}
