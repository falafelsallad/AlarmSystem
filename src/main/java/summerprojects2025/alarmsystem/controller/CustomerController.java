package summerprojects2025.alarmsystem.controller;


import org.springframework.web.bind.annotation.*;
import summerprojects2025.alarmsystem.model.Customer;
import summerprojects2025.alarmsystem.service.customer.implementation.CustomerServiceImpl;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    public final CustomerServiceImpl customerService;

    public CustomerController(CustomerServiceImpl customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customer")
    public String index() {
        return "Hello world";
    }

    // works adding the customers with JSON
    @PostMapping("/store")
    public Customer saveCustomer(@RequestBody Customer customer) {
        return customerService.create(customer);
    }

}
