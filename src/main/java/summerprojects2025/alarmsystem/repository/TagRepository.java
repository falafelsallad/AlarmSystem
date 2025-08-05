package summerprojects2025.alarmsystem.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import summerprojects2025.alarmsystem.model.CentralUnit;
import summerprojects2025.alarmsystem.model.Tag;

import java.util.List;
import java.util.Optional;

@Repository
public interface TagRepository extends JpaRepository <Tag, Long> {
    Optional<Tag> findByTagIdHash(String tagIdHash);
//    boolean existsByTagIdHashAndCentralUnit(String tagIdHash, CentralUnit centralUnit);
}
































