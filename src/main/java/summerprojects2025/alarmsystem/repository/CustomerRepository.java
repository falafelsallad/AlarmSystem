package summerprojects2025.alarmsystem.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import summerprojects2025.alarmsystem.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository <Customer, Long> {

    Customer findByEmail(String email);
}
