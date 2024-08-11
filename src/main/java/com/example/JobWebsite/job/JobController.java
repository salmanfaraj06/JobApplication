package com.example.JobWebsite.job;

import com.example.JobWebsite.company.Company;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {

    private JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    public ResponseEntity<List<Job>> findAll() {
        List<Job> jobs = jobService.findAll();
        return ResponseEntity.ok(jobs);
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody Job job) {
        jobService.createJob(job);
        return ResponseEntity.status(HttpStatus.CREATED).body("Job is created");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable Long id) {
        Job job = jobService.findById(id);
        if (job == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Job not found");
        }
        return ResponseEntity.ok(job);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable Long id) {
        boolean isDeleted = jobService.deleteById(id);
        if (!isDeleted) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Job not found");
        }
        return ResponseEntity.ok("Job deleted successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateById(@PathVariable Long id, @RequestBody Job job) {
        Job updatedJob = jobService.updateById(id, job);
        if (updatedJob == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Job not found");
        }
        return ResponseEntity.ok("Job updated successfully");
    }
}