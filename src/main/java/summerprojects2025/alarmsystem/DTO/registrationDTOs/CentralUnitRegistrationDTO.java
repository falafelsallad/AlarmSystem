package summerprojects2025.alarmsystem.DTO.registrationDTOs;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class CentralUnitRegistrationDTO {

    /// { id, userId: this.userId, name}

    private String serial; // id
    private String token; // jwt-token
    private String name; // name
}
