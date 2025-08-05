package summerprojects2025.alarmsystem.DTO;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthResponse {
    private String  token;
    private String email;

    public AuthResponse(String token, String email) {
        this.token = token;
        this.email = email;
    }

}
