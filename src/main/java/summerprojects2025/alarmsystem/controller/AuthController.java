package summerprojects2025.alarmsystem.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import summerprojects2025.alarmsystem.DTO.login.AuthRequest;
import summerprojects2025.alarmsystem.DTO.login.AuthResponse;
import summerprojects2025.alarmsystem.DTO.registrationDTOs.CustomerRegistrationDTO;
import summerprojects2025.alarmsystem.model.Customer;
import summerprojects2025.alarmsystem.service.customer.CustomerService;
import summerprojects2025.alarmsystem.service.LoginService;
import summerprojects2025.alarmsystem.utility.JwtUtil;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    /// Authentication flow:
    /// Customer submits credentials and is issued a Jwt token
    /// Client includes this token in Authorisation header for future requests
    /// JwtRequestFilter validates the token on each request
    /// and if it's valid Spring Security allows to access protected resources.

    private final LoginService loginService;
    private final CustomerService customerService;
    private final JwtUtil jwtUtil;

    public AuthController(LoginService loginService, CustomerService customerService, JwtUtil jwtUtil) {
        this.loginService = loginService;
        this.customerService = customerService;
        this.jwtUtil = jwtUtil;
    }
    /// AuthRequest class (DTO) has two variables: String email, String password
    /// AuthResponse class (DTO) has two variables: String token, String email
    /// TODO: Map out endpoints for retrieving data with authentication. Add filtering logic in services.
    /// {
    ///     "email": "alice@example.com",
    ///     "password": "abc123hash"
    /// }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest){
        try {
            Customer customer =  loginService.login(authRequest.getEmail(), authRequest.getPassword());
            String token = jwtUtil.generateToken(customer.getEmail());
            return ResponseEntity.ok(new AuthResponse(token, customer.getEmail()));
        } catch (RuntimeException e) {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }

    //Triggers validation for customer registration DTO
    @PostMapping("/register")
    public ResponseEntity<?> registerCustomer(@Valid @RequestBody CustomerRegistrationDTO registrationDTO){
        try {
            Customer registeredCustomer = customerService.register(registrationDTO);
            return ResponseEntity.ok("Registration successful" + "\nWelcome " + registeredCustomer.getName() + "!");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(@RequestHeader("Authorization") String authHeader) {
        String oldToken = authHeader.substring(7); //remove "Bearer " prefix
        if (jwtUtil.isTokenExpired(oldToken)) {
            String email = jwtUtil.extractEmail(oldToken);
            String newToken = jwtUtil.generateToken(email);
            return ResponseEntity.ok(Map.of("token", newToken));
        } else {
            return ResponseEntity.badRequest().body("Token is still valid");
        }
    }


}
