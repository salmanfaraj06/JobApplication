package com.example.JobWebsite.job;

import java.util.List;

public interface JobService {
    List<Job> findAll();
    void createJob(Job job);
    Job findById(Long id);

    boolean deleteById(Long id);

    Job updateById(Long id, Job job);



}
