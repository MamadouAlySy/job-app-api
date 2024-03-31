package mr.sy.aly.mamadou.jobapplicationapi.job;

import java.util.List;

public interface JobService {
    public List<Job> getAllJobs();
    public Job getJobById(Long id);
    public boolean createJob(Job job);
    public boolean updateJobById(Long id, Job updatedJobData);
    public boolean deleteJobById(Long id);
}
