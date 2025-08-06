package summerprojects2025.alarmsystem.service;

import org.springframework.stereotype.Service;
import summerprojects2025.alarmsystem.model.Customer;
import summerprojects2025.alarmsystem.repository.CustomerRepository;
import summerprojects2025.alarmsystem.utility.HashUtility;

import java.util.NoSuchElementException;

@Service
public class LoginService {

    private final CustomerRepository customerRepository;

    public LoginService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    // .equalsIgnoreCase for password hash, SHA256 doesn't use uppercase
    public Customer login(String email, String rawPassword) {
        Customer customer = customerRepository.findByEmail(email.toLowerCase())
                .orElseThrow(() -> new NoSuchElementException("Customer not found"));
        if (!HashUtility.SHA256(rawPassword).equalsIgnoreCase(customer.getPasswordHash())) {
            throw new RuntimeException("Invalid credentials");
        }
        return customer;
    }

}
