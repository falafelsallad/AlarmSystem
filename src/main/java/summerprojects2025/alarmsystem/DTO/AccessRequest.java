package summerprojects2025.alarmsystem.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccessRequest {
    private String serial;
    private String action;
    private String credentials;
    private String type;
}
