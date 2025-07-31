package summerprojects2025.alarmsystem.DTO;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.checkerframework.checker.regex.qual.Regex;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRegistrationDTO {


    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^(?:\\+46|0)7[02369]\\d{7}$", message = "Phone number must be Swedish")
    private String phonenumber;

    @NotBlank(message = "Valid Swedish personal number required")
    @Pattern(regexp = "^(\\d{2}){1,2}(0[1-9]|1[0-2])(0[1-9]|[12]\\d|3[01])[-+]?\\d{4}$",
            message = "Enter a valid Swedish personal number")
    private String personalNumber;

    @NotBlank
    private String address;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at leaast 6 characters")
    private String password;
}

