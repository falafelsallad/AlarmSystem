package summerprojects2025.alarmsystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import summerprojects2025.alarmsystem.DTO.CentralUnitWithUserDTO;
import summerprojects2025.alarmsystem.DTO.UserDTO;
import summerprojects2025.alarmsystem.DTO.registrationDTOs.AddNewUserCentralUnitDTO;
import summerprojects2025.alarmsystem.DTO.registrationDTOs.CentralUnitRegistrationDTO;
import summerprojects2025.alarmsystem.model.CentralUnit;
import summerprojects2025.alarmsystem.model.Customer;
import summerprojects2025.alarmsystem.security.SecurityService;
import summerprojects2025.alarmsystem.service.centralUnit.CentralUnitService;
import summerprojects2025.alarmsystem.service.customer.implementation.CustomerServiceImpl;
import summerprojects2025.alarmsystem.utility.JwtUtil;

import java.nio.file.AccessDeniedException;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/central-units")
@RequiredArgsConstructor
public class CentralUnitController {
    private final CentralUnitService centralUnitService;
    private final CustomerServiceImpl customerServiceImpl;
    private final SecurityService securityService;
    private final JwtUtil jwtUtil;

    //map out centralRegistrationDTO to match the incoming data.

    // POST, Registration of Central units to  a customer.
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/register")
    public ResponseEntity<CentralUnit> registerCentralUnitToCustomer(@RequestBody CentralUnitRegistrationDTO registrationDTO){

        String email = jwtUtil.extractEmail(registrationDTO.getToken());
        Optional<Customer> customer = customerServiceImpl.findByEmail(email);

        CentralUnit centralUnit = centralUnitService.registerCentralUnitToCustomer(customer.orElseThrow().getId(), registrationDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(centralUnit);
    }

    // POST, Registration of a new User to a central unit
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/user/register")
    public ResponseEntity<CentralUnit> addUserNewToCentralUnit(
            @RequestBody AddNewUserCentralUnitDTO addNewUserCentralUnitDTO) throws AccessDeniedException {

        securityService.checkCentralUnitOwnerShip(addNewUserCentralUnitDTO.getCentralUnitId());

        CentralUnit updatedCentralUnit = centralUnitService.addNewUserToCentralUnit(addNewUserCentralUnitDTO.getCentralUnitId(), addNewUserCentralUnitDTO.getName());
        return ResponseEntity.ok(updatedCentralUnit);
    }

    // Gets all Central Units by the customer
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<CentralUnit>> getCentralUnitsOwnedByCustomer(@PathVariable Long customerId) throws AccessDeniedException {

        securityService.checkCustomerOwnerShipMatch(customerId);
        List<CentralUnit> centralUnits = centralUnitService.findAllByCustomerId(customerId);
        return ResponseEntity.ok(centralUnits);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/customer/units")
    public ResponseEntity<List<CentralUnitWithUserDTO>> getCentralUnitForAuthenticatedCustomer() {
        String email = jwtUtil.extractEmailFromSecurityContext();
        Customer customer = customerServiceImpl.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));

        //geet central units for this customer
        List<CentralUnit> centralUnits = centralUnitService.findAllByCustomerId(customer.getId());

        // Map to DTOs
        List <CentralUnitWithUserDTO> centralUnitWithUserDTOs = centralUnits.stream()
                .map(unit -> {
                    CentralUnitWithUserDTO dto = new CentralUnitWithUserDTO();
                    dto.setName(unit.getName());
                    // TODO: central unit id

                    // create user map
                    Map<String, UserDTO> userDTOMap = new HashMap<>();
                    unit.getUsers().forEach(user -> {
                        UserDTO userDTO = new UserDTO();
                        userDTO.setName(user.getName());
//                        userDTO.setPassword(userDTO.getPassword());
                        userDTO.setPinhash(user.getPinHash());
                        userDTOMap.put(user.getName(), userDTO);
                    });
                    dto.setUsers(userDTOMap);
                    return dto;
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(centralUnitWithUserDTOs);
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<List<CentralUnit>> getAllCentralUnits() {
        List<CentralUnit> centralUnits = centralUnitService.findAll();
        return ResponseEntity.ok(centralUnits);
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/unoccupied")
    public ResponseEntity<List<CentralUnit>> getAllUnoccupiedCentralUnits() {
        List<CentralUnit> centralUnits = centralUnitService.findAllUnOccupiedCentralUnits();
        return ResponseEntity.ok(centralUnits);
    }

    //TODO: central unit delete, even deletes the tags connected to the central unit,
    // but users remain the same since they are under Customer Ownership.


}
