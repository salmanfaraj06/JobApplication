package com.example.JobWebsite.review;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies/{companyId}")
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> getAllReviews(@PathVariable Long companyId) {
        List<Review> reviews = reviewService.getAllReviews(companyId);
        if (reviews == null || reviews.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(reviews);
    }

   @PostMapping("/reviews")
    public ResponseEntity<String> addReview(@PathVariable Long companyId, @RequestBody Review review) {
    boolean isAdded = reviewService.addReview(companyId, review);
    if (!isAdded) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Company not found");
    }
        return ResponseEntity.ok("Review added successfully");
    }

    @GetMapping("/reviews/{reviewId}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long companyId, @PathVariable Long reviewId) {
        Review review = reviewService.getReviewById(companyId, reviewId);
        if (review == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(review);
    }

    @PutMapping("/reviews/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable Long companyId, @PathVariable Long reviewId, @RequestBody Review review) {
        boolean isUpdated = reviewService.updateReview(companyId, reviewId, review);
        if (!isUpdated) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Review not found");
        }
        return ResponseEntity.ok("Review updated successfully");
    }

    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long companyId, @PathVariable Long reviewId) {
        boolean isDeleted = reviewService.deleteReview(companyId, reviewId);
        if (!isDeleted) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Review not found");
        }
        return ResponseEntity.ok("Review deleted successfully");
    }


}
