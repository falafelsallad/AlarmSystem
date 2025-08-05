package summerprojects2025.alarmsystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import summerprojects2025.alarmsystem.DTO.UserRegistrationDTO;
import summerprojects2025.alarmsystem.model.User;
import summerprojects2025.alarmsystem.service.user.UserService;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/customer/{customerId}")
    public ResponseEntity<User> createUser(
            @PathVariable Long customerId,
            @RequestBody UserRegistrationDTO userDTO) {
        User user = userService.createUserForCustomer(customerId, userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        return userService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
