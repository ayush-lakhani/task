package com.zidio.connect.profile;

import com.zidio.connect.common.CurrentUser;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
public class StudentProfileController {
    private final StudentProfileRepository repository;

    public StudentProfileController(StudentProfileRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('STUDENT')")
    public ResponseEntity<?> get() {
        String email = CurrentUser.getEmail();
        return repository.findByUserEmail(email)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.ok(new StudentProfile()));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('STUDENT')")
    public ResponseEntity<StudentProfile> upsert(@Valid @RequestBody StudentProfile profile) {
        profile.setUserEmail(CurrentUser.getEmail());
        return ResponseEntity.ok(repository.save(profile));
    }
}

