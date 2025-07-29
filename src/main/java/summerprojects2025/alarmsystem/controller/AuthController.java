package summerprojects2025.alarmsystem.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import summerprojects2025.alarmsystem.model.Customer;
import summerprojects2025.alarmsystem.service.LoginService;

public class AuthController {

    private final LoginService loginService;


    public AuthController(LoginService loginService) {
        this.loginService = loginService;
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

}
