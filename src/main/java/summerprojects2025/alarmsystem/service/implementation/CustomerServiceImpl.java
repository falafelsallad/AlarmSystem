package summerprojects2025.alarmsystem.service.implementation;

import org.springframework.stereotype.Service;
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

    @Override
    public Customer create(Customer customer){
        return customerRepository.save(customer);
    }



}
