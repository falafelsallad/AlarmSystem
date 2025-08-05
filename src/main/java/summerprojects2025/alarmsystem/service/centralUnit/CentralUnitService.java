package summerprojects2025.alarmsystem.service.centralUnit;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import summerprojects2025.alarmsystem.DTO.CentralUnitRegistrationDTO;
import summerprojects2025.alarmsystem.model.CentralUnit;
import summerprojects2025.alarmsystem.model.Customer;
import summerprojects2025.alarmsystem.model.Tag;
import summerprojects2025.alarmsystem.model.User;
import summerprojects2025.alarmsystem.repository.CentralUnitRepository;
import summerprojects2025.alarmsystem.service.customer.implementation.CustomerServiceImpl;
import summerprojects2025.alarmsystem.service.user.UserService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CentralUnitService {

    private final CentralUnitRepository centralUnitRepository;
    private final CustomerServiceImpl customerService;
    private final UserService userService;

    public CentralUnit registerCentralUnitToCustomer(Long customerId, CentralUnitRegistrationDTO centralUnitRegDTO) {
        Customer customer = customerService.findById(customerId)
                .orElseThrow(() -> new NoSuchElementException("Customer not found"));
        CentralUnit centralUnit = new CentralUnit();
        centralUnit.setSerial(centralUnitRegDTO.getSerial());
        //TODO: CHANGE TO BOOLEAN
        centralUnit.setStatus(true);
        centralUnit.setCustomer(customer);

        return centralUnitRepository.save(centralUnit);
    }

    public CentralUnit addUserToCentralUnit(Long centralUnitId, Long userId) {
        CentralUnit centralUnit = centralUnitRepository.findById(centralUnitId)
                .orElseThrow(() -> new NoSuchElementException("Central unit by this ID not found"));
        User user = userService.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User by this ID not found"));

        // if user doesn't have any central units, create new list
        if (user.getCentralUnits() == null) {
            user.setCentralUnits(new HashSet<>());
        }
        user.getCentralUnits().add(centralUnit);
        userService.saveUserToCentralUnit(user);
        return centralUnit;
    }

    public void saveTagToCentralUnit(CentralUnit centralUnit) {
        centralUnitRepository.save(centralUnit);
    }

    public Optional<CentralUnit> findById(Long id) {
        return centralUnitRepository.findById(id);
    }


}
