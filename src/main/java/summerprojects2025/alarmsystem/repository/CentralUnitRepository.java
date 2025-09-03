package summerprojects2025.alarmsystem.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import summerprojects2025.alarmsystem.model.CentralUnit;

import java.util.List;
import java.util.Optional;

@Repository
public interface CentralUnitRepository extends JpaRepository <CentralUnit, Long> {

    Optional <CentralUnit> findBySerial(String serial);

    Optional<List<CentralUnit>> findCentralUnitByCustomer_Id(Long customerId);

    List<CentralUnit> findByCustomerIsNull();

    boolean existsBySerial(String serial);

}
