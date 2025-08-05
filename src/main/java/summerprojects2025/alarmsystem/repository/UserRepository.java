package summerprojects2025.alarmsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import summerprojects2025.alarmsystem.model.CentralUnit;
import summerprojects2025.alarmsystem.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

//    @Query("SELECT u FROM User u JOIN u.centralUnits cu WHERE cu = :centralUnit AND u.pinHash = :pin")
//    boolean existsByPinAndCentralUnit(@Param("pin") String pin, @Param("centralUnit") CentralUnit cu);

}
