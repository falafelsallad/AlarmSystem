package summerprojects2025.alarmsystem.service;

import summerprojects2025.alarmsystem.DTO.CustomerRegistrationDTO;
import summerprojects2025.alarmsystem.model.Customer;

public interface CustomerService {
    public Customer create(Customer customer);
    public Customer register(CustomerRegistrationDTO dto);
}
