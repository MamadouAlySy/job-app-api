package mr.sy.aly.mamadou.jobapplicationapi.job;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/jobs")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    public ResponseEntity<List<Job>> index() {
        return new ResponseEntity<>(
                jobService.getAllJobs(),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity show(@PathVariable Long id) {
        Job job = jobService.getJobById(id);
        if (job == null)
            return new ResponseEntity<>("Job not found.", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(job, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Job> create(@RequestBody Job job) {
        return new ResponseEntity<>(jobService.createJob(job), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable Long id,  @RequestBody Job job) {
        Job updatedJob = jobService.updateJobById(id, job);
        if (job == null)
            return new ResponseEntity<>("Job not found.", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(updatedJob, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        Job job = jobService.getJobById(id);
        if (job == null)
            return new ResponseEntity<>("Job not found.", HttpStatus.NOT_FOUND);
        jobService.deleteJobById(id);
        return new ResponseEntity<>(job, HttpStatus.OK);
    }
}
