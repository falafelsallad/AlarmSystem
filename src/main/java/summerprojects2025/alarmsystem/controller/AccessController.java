package summerprojects2025.alarmsystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import summerprojects2025.alarmsystem.DTO.AccessRequest;
import summerprojects2025.alarmsystem.service.AccessControlService;

@RestController
@RequestMapping("/access")
@RequiredArgsConstructor
public class AccessController {

    private final AccessControlService accessControlService;

    //Using ResponseEntity represent the HTTP response
//    @PostMapping
//    public ResponseEntity<String> verify(@RequestBody AccessRequest request) {
//        boolean allowed = accessControlService.verifyAccess(request);
//        return allowed ? ResponseEntity.ok("Verified. Access granted") : ResponseEntity.status(403).body("Not Verified. Access Denied");
//    }

    //testing ResponseEntity
    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("Hello World");
    }


}
