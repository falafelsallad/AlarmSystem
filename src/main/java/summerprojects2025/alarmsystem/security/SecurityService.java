package summerprojects2025.alarmsystem.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import summerprojects2025.alarmsystem.model.CentralUnit;
import summerprojects2025.alarmsystem.model.Customer;
import summerprojects2025.alarmsystem.model.User;
import summerprojects2025.alarmsystem.service.centralUnit.CentralUnitService;
import summerprojects2025.alarmsystem.service.customer.implementation.CustomerServiceImpl;
import summerprojects2025.alarmsystem.service.user.UserService;

import java.nio.file.AccessDeniedException;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class SecurityService {
    private final CustomerServiceImpl customerService;
    private final UserService userService;
    private final CentralUnitService centralUnitService;

    public Customer getAuthenticatedCustomer() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return customerService.findByEmail(email)
                .orElseThrow(() -> new NoSuchElementException("Security Service message: Authenticated customer not found"));
    }

    public void checkCustomerOwnerShipMatch(Long customerId) throws AccessDeniedException {
        Customer currentCustomer = getAuthenticatedCustomer();
        if (!currentCustomer.getId().equals(customerId)){
            throw new AccessDeniedException("Security Service message: Customer did not match authenticated customer");
        }
    }

    public void checkUserOwnerShip(Long userId) throws AccessDeniedException {
        Customer currentCustomer = getAuthenticatedCustomer();
        User user = userService.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("Security Service message: User not found"));
        if (!user.getCustomer().getId().equals(currentCustomer.getId())){
            throw new AccessDeniedException("You do not own this user.");
        }
    }

    public void checkCentralUnitOwnerShip(Long centralUnitId) throws AccessDeniedException {
        Customer currentCustomer = getAuthenticatedCustomer();
        CentralUnit centralUnit = centralUnitService.findById(centralUnitId)
                .orElseThrow(() -> new NoSuchElementException("Security Service message: Central unit not found"));
        if (!centralUnit.getCustomer().getId().equals(currentCustomer.getId())) {
            throw new AccessDeniedException("You do not own this central unit.");
        }
    }


}
