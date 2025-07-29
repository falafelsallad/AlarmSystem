package summerprojects2025.alarmsystem.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRegistrationDTO {
    private String name;
    private String email;
    private String phonenumber;
    private String personalNumber;
    private String address;
    private String password;
}

