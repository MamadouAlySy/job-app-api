package mr.sy.aly.mamadou.jobapplicationapi.review;

import mr.sy.aly.mamadou.jobapplicationapi.company.Company;
import mr.sy.aly.mamadou.jobapplicationapi.company.CompanyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImplementation implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final CompanyService companyService;

    public ReviewServiceImplementation(ReviewRepository reviewRepository, CompanyService companyService) {
        this.reviewRepository = reviewRepository;
        this.companyService = companyService;
    }

    @Override
    public List<Review> getAllReviewsByCompanyId(Long companyId) {
        return reviewRepository.findByCompanyId(companyId);
    }

    @Override
    public Review getReviewByCompanyIdAndId(Long companyId, Long reviewId) {
        List<Review> listOfReviews = getAllReviewsByCompanyId(companyId);
        return listOfReviews
                .stream()
                .filter(review -> review.getId().equals(reviewId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Review addNewReview(Review review, Long companyId) {
        Company company = companyService.getCompanyById(companyId);
        review.setCompany(company);
        return reviewRepository.save(review);
    }

    @Override
    public Review updateReviewByCompanyIdAndId(Long companyId, Long reviewId, Review review) {
        Review oldReview = getReviewByCompanyIdAndId(companyId, reviewId);
        if (oldReview != null) {
            oldReview.setTitle(review.getTitle() == null ? oldReview.getTitle() : review.getTitle());
            oldReview.setDescription(review.getDescription() == null ? oldReview.getDescription() : review.getDescription());
            oldReview.setRating(review.getRating() == null ? oldReview.getRating() : review.getRating());
            return reviewRepository.save(oldReview);
        }
        return null;
    }

    @Override
    public void deleteReviewByCompanyIdAndId(Long companyId, Long reviewId) {
        Review oldReview = getReviewByCompanyIdAndId(companyId, reviewId);
        if (oldReview != null) {
            reviewRepository.delete(oldReview);
        }
    }
}
