package com.example.JobWebsite.review.impl;

import com.example.JobWebsite.company.Company;
import com.example.JobWebsite.company.CompanyService;
import com.example.JobWebsite.review.Review;
import com.example.JobWebsite.review.ReviewRepository;
import com.example.JobWebsite.review.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final CompanyService companyService;

    public ReviewServiceImpl(ReviewRepository reviewRepository, CompanyService companyService) {
        this.reviewRepository = reviewRepository;
        this.companyService = companyService;
    }

    @Override
    public List<Review> getAllReviews(Long companyId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        return reviews;
    }

    @Override
    public boolean addReview(Long companyId, Review review) {
        Company company = companyService.getCompanyById(companyId);
        if (company == null) {
            return false;
        }
        review.setCompany(company);
        reviewRepository.save(review);
        return true;

    }

    @Override
    public Review getReviewById(Long companyId, Long reviewId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        for (Review review : reviews) {
            if (review.getId().equals(reviewId)) {
                return review;
            }
        }
        return null;
    }

    @Override
    public boolean updateReview(Long companyId, Long reviewId, Review review) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        for (Review r : reviews) {
            if (r.getId().equals(reviewId)) {
                r.setTitle(review.getTitle());
                r.setDescription(review.getDescription());
                r.setRating(review.getRating());
                reviewRepository.save(r);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteReview(Long companyId, Long reviewId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        for (Review review : reviews) {
            if (review.getId().equals(reviewId)) {
                reviewRepository.delete(review);
                return true;
            }
        }
        return false;
    }


}
