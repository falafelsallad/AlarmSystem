package summerprojects2025.alarmsystem.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import summerprojects2025.alarmsystem.DTO.CustomerRegistrationDTO;
import summerprojects2025.alarmsystem.model.Customer;
import summerprojects2025.alarmsystem.service.CustomerService;
import summerprojects2025.alarmsystem.service.LoginService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final LoginService loginService;
    private final CustomerService customerService;

    public AuthController(LoginService loginService, CustomerService customerService) {
        this.loginService = loginService;
        this.customerService = customerService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String email, String password){
        try {
            Customer customer =  loginService.login(email, password);
            return ResponseEntity.ok(customer);
        } catch (RuntimeException e) {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }

    @PostMapping("register")
    public ResponseEntity<?> registerCustomer(@Valid @RequestBody CustomerRegistrationDTO registrationDTO){
        try {
            Customer registeredCustomer = customerService.register(registrationDTO);
            return ResponseEntity.ok("Registration successful");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
