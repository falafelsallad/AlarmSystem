package summerprojects2025.alarmsystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import summerprojects2025.alarmsystem.DTO.CentralUnitRegistrationDTO;
import summerprojects2025.alarmsystem.model.CentralUnit;
import summerprojects2025.alarmsystem.service.centralUnit.CentralUnitService;

@RestController
@RequestMapping("/api/central-units")
@RequiredArgsConstructor
public class CentralUnitController {
    private final CentralUnitService centralUnitService;

    //map out centralRegistrationDTO to match the incoming data.

    @PostMapping("/customer/{customerId}")
    public ResponseEntity<CentralUnit> registerCentralUnitToCustomer(@PathVariable Long customerId,
            @RequestBody CentralUnitRegistrationDTO registrationDTO) {
        CentralUnit centralUnit = centralUnitService.registerCentralUnitToCustomer(customerId, registrationDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(centralUnit);
    }

    @PostMapping("/{centralUnitId}/users/{userId}")
    public ResponseEntity<CentralUnit> addUserToCentralUnit(
            @PathVariable Long centralUnitId,
            @PathVariable Long userId) {
        CentralUnit centralUnit = centralUnitService.addUserToCentralUnit(centralUnitId, userId);
        return ResponseEntity.ok(centralUnit);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CentralUnit> getCentralUnit(@PathVariable Long id) {
        return centralUnitService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


}
