package mr.sy.aly.mamadou.jobapplicationapi.review;

import java.util.List;

public interface ReviewService {
    public List<Review> getAllReviewsByCompanyId(Long companyId);
    public Review getReviewByCompanyIdAndId(Long companyId, Long reviewId);
    public Review addNewReview(Review review, Long companyId);
    public Review updateReviewByCompanyIdAndId(Long companyId, Long reviewId, Review review);
    public void deleteReviewByCompanyIdAndId(Long companyId, Long reviewId);
}
