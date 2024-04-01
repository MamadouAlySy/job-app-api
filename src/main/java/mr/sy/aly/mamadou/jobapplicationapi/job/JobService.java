package mr.sy.aly.mamadou.jobapplicationapi.job;

import java.util.List;

public interface JobService {
    public List<Job> getAllJobs();
    public Job getJobById(Long id);
    public Job createJob(Job job);
    public Job updateJobById(Long id, Job job);
    public void deleteJobById(Long id);
}
