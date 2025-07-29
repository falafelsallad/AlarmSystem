package summerprojects2025.alarmsystem.service;

import org.springframework.stereotype.Service;
import summerprojects2025.alarmsystem.model.Customer;
import summerprojects2025.alarmsystem.repository.CustomerRepository;
import summerprojects2025.alarmsystem.utility.HashUtility;

@Service
public class LoginService {

    private final CustomerRepository customerRepository;

    public LoginService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer login(String email, String rawPassword) {
        Customer customer = customerRepository.findByEmail(email);
        if (customer == null) throw new RuntimeException("Customer not found");
        if (!HashUtility.SHA256(rawPassword).equalsIgnoreCase(customer.getPasswordHash())) {
            throw new RuntimeException("Invalid credentials");
        }
        return customer;
    }

}
