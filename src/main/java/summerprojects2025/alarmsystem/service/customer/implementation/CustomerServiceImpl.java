package summerprojects2025.alarmsystem.service.customer.implementation;

import org.springframework.stereotype.Service;
import summerprojects2025.alarmsystem.DTO.CustomerRegistrationDTO;
import summerprojects2025.alarmsystem.model.Customer;
import summerprojects2025.alarmsystem.repository.CustomerRepository;
import summerprojects2025.alarmsystem.service.customer.CustomerService;
import summerprojects2025.alarmsystem.utility.HashUtility;

import java.util.Optional;


@Service
public class CustomerServiceImpl implements CustomerService {
    //TODO: Hashing passwords

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public String hashPasswordSHA256(String rawPassword) {
        return HashUtility.SHA256(rawPassword);
    }


    // Hashing password and setting all values from DTO to Customer object.
    @Override
    public Customer register(CustomerRegistrationDTO dto) {

        if (customerRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("A customer with this email already exists");
        }

        if (customerRepository.existsBySsn(dto.getPersonalNumber())) {
            throw new RuntimeException("A customer with this personal number already exists");
        }

        Customer customer = new Customer();
        customer.setName(dto.getName());
        customer.setEmail(dto.getEmail());
        customer.setPhoneNumber(dto.getPhone());
        customer.setSsn(dto.getPersonalNumber());
        customer.setAddress(dto.getAddress());
        customer.setPasswordHash(hashPasswordSHA256(dto.getPassword()));

        return customerRepository.save(customer);
    }

    // Hashing password even for @PostMapping of Customers
    @Override
    public Customer create(Customer customer) {
        customer.setPasswordHash(hashPasswordSHA256(customer.getPasswordHash()));
        return customerRepository.save(customer);
    }

    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);
    }




}
