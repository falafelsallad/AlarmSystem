package summerprojects2025.alarmsystem.service.implementation;

import org.checkerframework.checker.units.qual.C;
import org.springframework.stereotype.Service;
import summerprojects2025.alarmsystem.DTO.CustomerRegistrationDTO;
import summerprojects2025.alarmsystem.model.Customer;
import summerprojects2025.alarmsystem.repository.CustomerRepository;
import summerprojects2025.alarmsystem.service.CustomerService;
import summerprojects2025.alarmsystem.utility.HashUtility;


@Service
public class CustomerServiceImpl implements CustomerService {
    //TODO: Hashing passwords

    public final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public String hashPasswordSHA256(String rawPassword) {
        return HashUtility.SHA256(rawPassword);
    }


    // Hashing password and setting all values from DTO to Customer object.
    @Override
    public Customer register(CustomerRegistrationDTO dto) {
        Customer customer = new Customer();
        customer.setName(dto.getName());
        customer.setEmail(dto.getEmail());
        customer.setPhoneNumber(dto.getPhonenumber());
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





}
