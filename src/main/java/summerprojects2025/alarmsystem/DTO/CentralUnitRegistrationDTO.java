package summerprojects2025.alarmsystem.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class CentralUnitRegistrationDTO {
    private String serial;
    private Long customerId;
}
