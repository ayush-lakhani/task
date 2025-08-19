package com.zidio.connect.application;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {
    List<JobApplication> findByStudentEmail(String studentEmail);
    List<JobApplication> findByJobId(Long jobId);
}

