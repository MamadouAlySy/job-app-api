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
        List<Job> listOfJobs = jobService.getAllJobs();
        return new ResponseEntity<>(listOfJobs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> show(@PathVariable Long id) {
        Job job = jobService.getJobById(id);
        if (job == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(job, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<String> create(@RequestBody Job job) {
        if (jobService.createJob(job)) {
            return new ResponseEntity<>("Job created successfully", HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Long id,  @RequestBody Job job) {
        if (jobService.updateJobById(id, job)) {
            return new ResponseEntity<>("Job updated successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        if (jobService.deleteJobById(id)) {
            return new ResponseEntity<>("Job deleted successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
