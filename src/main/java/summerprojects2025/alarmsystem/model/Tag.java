package summerprojects2025.alarmsystem.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Tag {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tagIdHash;

    @ManyToOne(optional = false)
    private CentralUnit centralUnit;

    @ManyToOne(optional = true)
    private User user;
}
