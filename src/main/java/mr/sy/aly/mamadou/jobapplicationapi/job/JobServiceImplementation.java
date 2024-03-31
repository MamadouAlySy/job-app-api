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
    public boolean createJob(Job job) {
        try {
            jobRepository.save(job);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updateJobById(Long id, Job updatedJobData) {
        try {
            Optional<Job> jobOptional = jobRepository.findById(id);
            if (jobOptional.isPresent()) {
                Job job = jobOptional.get();
                job.setTitle(updatedJobData.getTitle());
                job.setDescription(updatedJobData.getDescription());
                job.setMinimumSalary(updatedJobData.getMinimumSalary());
                job.setMaximumSalary(updatedJobData.getMaximumSalary());
                job.setLocation(updatedJobData.getLocation());
                jobRepository.save(job);
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean deleteJobById(Long id) {
        try {
            jobRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
