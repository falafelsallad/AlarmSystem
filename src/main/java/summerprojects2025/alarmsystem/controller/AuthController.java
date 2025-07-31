package summerprojects2025.alarmsystem.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import summerprojects2025.alarmsystem.DTO.AuthResponse;
import summerprojects2025.alarmsystem.DTO.CustomerRegistrationDTO;
import summerprojects2025.alarmsystem.model.Customer;
import summerprojects2025.alarmsystem.service.CustomerService;
import summerprojects2025.alarmsystem.service.LoginService;
import summerprojects2025.alarmsystem.utility.JwtUtil;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {


    private final LoginService loginService;
    private final CustomerService customerService;
    private final JwtUtil jwtUtil;

    public AuthController(LoginService loginService, CustomerService customerService, JwtUtil jwtUtil) {
        this.loginService = loginService;
        this.customerService = customerService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String email, @RequestParam String password){
        try {
            Customer customer =  loginService.login(email, password);
            String token = jwtUtil.generateToken(customer.getEmail());
            return ResponseEntity.ok(new AuthResponse(token, customer.getEmail()));
        } catch (RuntimeException e) {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerCustomer(@Valid @RequestBody CustomerRegistrationDTO registrationDTO){
        try {
            Customer registeredCustomer = customerService.register(registrationDTO);
            return ResponseEntity.ok("Registration successful");
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
