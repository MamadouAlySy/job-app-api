package mr.sy.aly.mamadou.jobapplicationapi.job;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImplementation implements JobService {

    private final JobRepository jobRepository;

    public JobServiceImplementation(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    @Override
    public Job getJobById(Long id) {
        return jobRepository.findById(id)
                .orElse(null);
    }

    @Override
    public Job createJob(Job job) {
        Job createdJob = jobRepository.save(job);
        return getJobById(createdJob.getId());
    }

    @Override
    public Job updateJobById(Long id, Job updatedJobData) {
        Optional<Job> jobOptional = jobRepository.findById(id);
        if (jobOptional.isPresent()) {
            Job job = jobOptional.get();
            job.setTitle(updatedJobData.getTitle() == null ? job.getTitle() : updatedJobData.getTitle());
            job.setDescription(updatedJobData.getDescription() == null ? job.getDescription() : updatedJobData.getDescription());
            job.setMinimumSalary(updatedJobData.getMinimumSalary() == null ? job.getMinimumSalary() : updatedJobData.getMinimumSalary());
            job.setMaximumSalary(updatedJobData.getMaximumSalary() == null ? job.getMaximumSalary() : updatedJobData.getMaximumSalary());
            job.setLocation(updatedJobData.getLocation() == null ? job.getLocation() : updatedJobData.getLocation());
            return jobRepository.save(job);
        }
        return null;
    }

    @Override
    public void deleteJobById(Long id) {
        jobRepository.deleteById(id);
    }
}
