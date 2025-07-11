package summerprojects2025.alarmsystem.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import summerprojects2025.alarmsystem.model.CentralUnit;
import summerprojects2025.alarmsystem.model.Tag;

@Repository
public interface TagRepository extends JpaRepository <Tag, Long> {
    boolean existsByTagIdHashAndCentralUnit(String tagIdHash, CentralUnit centralUnit);
}
