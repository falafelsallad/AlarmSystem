package summerprojects2025.alarmsystem.DTO;

import lombok.Data;

import java.util.Map;

@Data
public class CentralUnitWithUserDTO {
    private String name;
    private Map<String, UserDTO> userDTOMap;
//    private Map<String, TagDTO> tagDTOMap;

}
