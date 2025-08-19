package com.zidio.connect.job;

import com.zidio.connect.common.CurrentUser;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
public class JobPostingController {
    private final JobPostingRepository repository;

    public JobPostingController(JobPostingRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<JobPosting> list() {
        return repository.findAll();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('RECRUITER') or hasAuthority('ADMIN')")
    public ResponseEntity<JobPosting> create(@Valid @RequestBody JobPosting posting) {
        posting.setRecruiterEmail(CurrentUser.getEmail());
        return ResponseEntity.ok(repository.save(posting));
    }
}

