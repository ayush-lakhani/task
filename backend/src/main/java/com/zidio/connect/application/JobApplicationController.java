package com.zidio.connect.application;

import com.zidio.connect.common.CurrentUser;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
public class JobApplicationController {
    private final JobApplicationRepository repository;

    public JobApplicationController(JobApplicationRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/apply/{jobId}")
    @PreAuthorize("hasAuthority('STUDENT')")
    public ResponseEntity<JobApplication> apply(@PathVariable Long jobId) {
        JobApplication app = new JobApplication();
        app.setJobId(jobId);
        app.setStudentEmail(CurrentUser.getEmail());
        app.setStatus("APPLIED");
        return ResponseEntity.ok(repository.save(app));
    }

    @GetMapping("/me")
    @PreAuthorize("hasAuthority('STUDENT')")
    public List<JobApplication> myApplications() {
        return repository.findByStudentEmail(CurrentUser.getEmail());
    }

    @PostMapping("/{id}/status/{status}")
    @PreAuthorize("hasAuthority('RECRUITER') or hasAuthority('ADMIN')")
    public ResponseEntity<JobApplication> updateStatus(@PathVariable Long id, @PathVariable String status) {
        return repository.findById(id)
                .map(app -> { app.setStatus(status.toUpperCase()); return ResponseEntity.ok(repository.save(app)); })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}

