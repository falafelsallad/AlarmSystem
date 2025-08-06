package summerprojects2025.alarmsystem.repository;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import summerprojects2025.alarmsystem.model.Customer;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository <Customer, Long> {

    Optional<Customer> findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsBySsn(String ssn);
}
