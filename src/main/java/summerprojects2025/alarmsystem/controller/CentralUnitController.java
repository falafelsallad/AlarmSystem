package summerprojects2025.alarmsystem.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import summerprojects2025.alarmsystem.DTO.registrationDTOs.CentralUnitRegistrationDTO;
import summerprojects2025.alarmsystem.model.CentralUnit;
import summerprojects2025.alarmsystem.security.SecurityService;
import summerprojects2025.alarmsystem.service.centralUnit.CentralUnitService;
import summerprojects2025.alarmsystem.service.customer.implementation.CustomerServiceImpl;

import java.nio.file.AccessDeniedException;
import java.util.List;

@RestController
@RequestMapping("/api/central-units")
@RequiredArgsConstructor
public class CentralUnitController {
    private final CentralUnitService centralUnitService;
    private final CustomerServiceImpl customerServiceImpl;
    private final SecurityService securityService;

    //map out centralRegistrationDTO to match the incoming data.

    // POST, Registration of Central units to  a customer.
    @PostMapping("/customer/{customerId}")
    public ResponseEntity<CentralUnit> registerCentralUnitToCustomer(@PathVariable Long customerId,
            @RequestBody CentralUnitRegistrationDTO registrationDTO) throws AccessDeniedException {

        securityService.checkCustomerOwnerShipMatch(customerId);

        CentralUnit centralUnit = centralUnitService.registerCentralUnitToCustomer(customerId, registrationDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(centralUnit);
    }

    // POST, Registration of User to a central unit
    @PostMapping("/{centralUnitId}/users/{userId}")
    public ResponseEntity<CentralUnit> addUserToCentralUnit(
            @PathVariable Long centralUnitId,
            @PathVariable Long userId) throws AccessDeniedException {

        securityService.checkCentralUnitOwnerShip(centralUnitId);

        CentralUnit updatedCentralUnit = centralUnitService.addUserToCentralUnit(centralUnitId, userId);
        return ResponseEntity.ok(updatedCentralUnit);
    }

    // Gets all Central Units by the customer
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<CentralUnit>> getCentralUnitsOwnedByCustomer(@PathVariable Long customerId) throws AccessDeniedException {

        securityService.checkCustomerOwnerShipMatch(customerId);
        List<CentralUnit> centralUnits = centralUnitService.findAllByCustomerId(customerId);
        return ResponseEntity.ok(centralUnits);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<List<CentralUnit>> getAllCentralUnits() {
        List<CentralUnit> centralUnits = centralUnitService.findAll();
        return ResponseEntity.ok(centralUnits);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/unoccupied")
    public ResponseEntity<List<CentralUnit>> getAllUnoccupiedCentralUnits() {
        List<CentralUnit> centralUnits = centralUnitService.findAllUnOccupiedCentralUnits();
        return ResponseEntity.ok(centralUnits);
    }



    //TODO: central unit delete, even deletes the tags connected to the central unit,
    // but users remain the same since they are under Customer Ownership.


}
