package com.hmsapp.Controller;

import com.hmsapp.Entity.Reviews;
import com.hmsapp.Entity.User;
import com.hmsapp.PayLoad.ReviewsDto;
import com.hmsapp.Service.PropertyService;
import com.hmsapp.Service.ReviewsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {
    public ReviewController(PropertyService propertyService, ReviewsService reviewsService) {
        this.propertyService = propertyService;
        this.reviewsService = reviewsService;
    }

    PropertyService propertyService;
    ReviewsService reviewsService;

    @PostMapping("/addreview")
    public ResponseEntity<?> addRev(
            @RequestBody ReviewsDto reviewsDto,
            @RequestParam long propertyId,
            @AuthenticationPrincipal User user
            ){
        return new ResponseEntity<>(reviewsService.addReview(reviewsDto,propertyId,user), HttpStatus.CREATED);
    }

    @GetMapping("/reviews")
    public ResponseEntity<List<?>> getMyReviews(@AuthenticationPrincipal User user){

        return new ResponseEntity<>(reviewsService.getMyReviews(user),HttpStatus.OK);
    }

}
