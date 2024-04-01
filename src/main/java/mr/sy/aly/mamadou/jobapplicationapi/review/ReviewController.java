package mr.sy.aly.mamadou.jobapplicationapi.review;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/company/{companyId}/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping()
    public ResponseEntity<List<Review>> index(@PathVariable Long companyId) {
        return new ResponseEntity<>(
                reviewService.getAllReviewsByCompanyId(companyId),
                HttpStatus.OK
        );
    }

    @GetMapping(path = "/{reviewId}")
    public ResponseEntity show(@PathVariable Long companyId, @PathVariable Long reviewId) {
        Review review = reviewService.getReviewByCompanyIdAndId(companyId, reviewId);
        if (review == null)
            return new ResponseEntity<>("Review not found.", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(review, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Review> create(@RequestBody Review review, @PathVariable Long companyId) {
        return new ResponseEntity<>(
                reviewService.addNewReview(review, companyId),
                HttpStatus.CREATED
        );
    }

    @PutMapping(path = "/{reviewId}")
    public ResponseEntity update(@PathVariable Long companyId, @PathVariable Long reviewId,  @RequestBody Review review) {
        Review updatedReview= reviewService.updateReviewByCompanyIdAndId(companyId, reviewId, review);
        if (review == null)
            return new ResponseEntity<>("Review not found.", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(updatedReview, HttpStatus.OK);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity delete(@PathVariable Long companyId, @PathVariable Long reviewId) {
        Review review = reviewService.getReviewByCompanyIdAndId(companyId, reviewId);
        if (review == null)
            return new ResponseEntity<>("Review not found.", HttpStatus.NOT_FOUND);
        reviewService.deleteReviewByCompanyIdAndId(companyId, reviewId);
        return new ResponseEntity<>(review, HttpStatus.OK);
    }
}
