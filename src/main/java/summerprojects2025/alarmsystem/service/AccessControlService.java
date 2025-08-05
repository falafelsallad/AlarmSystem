package summerprojects2025.alarmsystem.service;

import org.springframework.stereotype.Service;
import summerprojects2025.alarmsystem.DTO.AccessRequest;
import summerprojects2025.alarmsystem.model.CentralUnit;
import summerprojects2025.alarmsystem.repository.CentralUnitRepository;
import summerprojects2025.alarmsystem.repository.TagRepository;
import summerprojects2025.alarmsystem.repository.UserRepository;

import java.util.Optional;

@Service
public class AccessControlService {
    private final CentralUnitRepository centralUnitRepo;

    private final TagRepository tagRepo;

    private final UserRepository userRepo;

    public AccessControlService(CentralUnitRepository centralUnitRepo, TagRepository tagRepo, UserRepository userRepo) {
        this.centralUnitRepo = centralUnitRepo;
        this.tagRepo = tagRepo;
        this.userRepo = userRepo;
    }

//    public boolean verifyAccess(AccessRequest req) {
//        Optional<CentralUnit> optionalCu = centralUnitRepo.findBySerial(req.getSerial());
//        if (optionalCu.isEmpty()) return false;
//
//        CentralUnit cu = optionalCu.get();
//
//        return switch (req.getType().toLowerCase()) {
//            case "tag" -> tagRepo.existsByTagIdHashAndCentralUnit(req.getCredentials(), cu);
//            case "pin" -> userRepo.existsByPinAndCentralUnit(req.getCredentials(), cu);
//            default -> false;
//        };
//
//    }
}
