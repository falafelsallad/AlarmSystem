package summerprojects2025.alarmsystem.service.tag;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import summerprojects2025.alarmsystem.DTO.registrationDTOs.TagRegistrationDTO;
import summerprojects2025.alarmsystem.model.CentralUnit;
import summerprojects2025.alarmsystem.model.Tag;
import summerprojects2025.alarmsystem.repository.TagRepository;
import summerprojects2025.alarmsystem.service.centralUnit.CentralUnitService;
import summerprojects2025.alarmsystem.utility.HashUtility;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class TagService {
    private final TagRepository tagRepository;
    private final CentralUnitService centralUnitService;

    @Transactional
    public Tag connectTagToCentralUnit(Long centralUnitId, TagRegistrationDTO tagRegDTO){
        CentralUnit centralUnit = centralUnitService.findById(centralUnitId)
                .orElseThrow(() -> new NoSuchElementException("Central unit by this ID not found"));

        Tag tag = findOrCreateTagByHash(HashUtility.SHA256(tagRegDTO.getTagId()));

        //connecting tag  to central unit.
        tag.getCentralUnits().add(centralUnit);
        centralUnit.getTags().add(tag);

        return tag;
    }

    public Tag findOrCreateTagByHash (String tagHash) {
        return tagRepository.findByTagIdHash(tagHash)
                .orElseGet(() -> {
                    Tag newTag = new Tag();
                    newTag.setTagIdHash(tagHash);
                    return tagRepository.save(newTag);
                });
    }

    public List<Tag> getTagsByCentralUnitId(Long centralUnitId){
        CentralUnit centralUnit = centralUnitService.findById(centralUnitId)
                .orElseThrow(() -> new NoSuchElementException("Central unit not found"));
        return centralUnit.getTags().stream().toList();
    }

}
