package summerprojects2025.alarmsystem.DTO.registrationDTOs;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class CentralUnitRegistrationDTO {

    /// { id, userId: this.userId, name}

    private String serial; // id
    private Long customerId; // userId
    private String name; // name
}
