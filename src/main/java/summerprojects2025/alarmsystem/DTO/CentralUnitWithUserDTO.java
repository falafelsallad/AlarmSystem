package summerprojects2025.alarmsystem.DTO;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class CentralUnitWithUserDTO {
    private Long id;
    private String name;
    private Boolean status;
    private List<UserDTO> users;


}
