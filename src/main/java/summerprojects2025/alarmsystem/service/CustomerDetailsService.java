package summerprojects2025.alarmsystem.service;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import summerprojects2025.alarmsystem.repository.CustomerRepository;
import summerprojects2025.alarmsystem.model.Customer;
import java.util.Collections;
import java.util.NoSuchElementException;

@Service
public class CustomerDetailsService implements UserDetailsService {

    //connecting customerRepo to Spring Security

    private final CustomerRepository customerRepository;

    public CustomerDetailsService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Customer customer =  customerRepository.findByEmail(email)
                .orElseThrow(() ->  new NoSuchElementException("Customer not found " + email));
//        List<GrantedAuthority> authorities = customer.getRoles().stream()
//                .map(role -> new SimpleGrantedAuthority(role.getName()))
//                .collect(Collectors.toList());
//        return new User(customer.getEmail(), customer.getPasswordHash(), authorities);
        return new User(
                customer.getEmail(),
                customer.getPasswordHash(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_CUSTOMER"))
        );
    }
}
