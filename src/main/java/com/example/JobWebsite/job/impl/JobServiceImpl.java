package com.example.JobWebsite.job.impl;
import com.example.JobWebsite.job.Job;
import com.example.JobWebsite.job.JobRepository;
import com.example.JobWebsite.job.JobService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {
    JobRepository jobRepository;
     private Long Nextid = 1L;     
    
    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }
    @Override
    public List<Job> findAll() {
        return jobRepository.findAll();
    }

    @Override
    public void createJob(Job job) {
        job.setId(Nextid++);
        jobRepository.save(job);
    }

    @Override
    public Job findById(Long id) {
        return jobRepository.findById(id).orElse(null);
    }
    @Override
    public boolean deleteById(Long id) {
        if (jobRepository.existsById(id)) {
            jobRepository.deleteById(id);
            return true;
        }
        return false;
    }

        @Override
        public Job updateById (Long id, Job job){
            Optional<Job> jobOptional = jobRepository.findById(id);
            if (jobOptional.isPresent()) {
                Job job1 = jobOptional.get();
                job1.setTitle(job.getTitle());
                job1.setDescription(job.getDescription());
                job1.setMinSalary(job.getMinSalary());
                job1.setMaxSalary(job.getMaxSalary());
                job1.setLocation(job.getLocation());
                return jobRepository.save(job1);
            }
            return null;
        }
}
