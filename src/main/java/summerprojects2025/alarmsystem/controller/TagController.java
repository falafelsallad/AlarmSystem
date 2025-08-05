package summerprojects2025.alarmsystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import summerprojects2025.alarmsystem.model.Tag;
import summerprojects2025.alarmsystem.service.tag.TagService;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class TagController {
    private final TagService tagService;

}
