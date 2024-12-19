package com.hmsapp.Service;

import com.hmsapp.Entity.Property;
import com.hmsapp.Entity.Reviews;
import com.hmsapp.Entity.User;
import com.hmsapp.PayLoad.ReviewsDto;
import com.hmsapp.Repository.PropertyRepository;
import com.hmsapp.Repository.ReviewsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewsService {
    public ReviewsService(ModelMapper modelMapper, PropertyRepository propertyRepository, ReviewsRepository reviewsRepository) {
        this.modelMapper = modelMapper;
        this.propertyRepository = propertyRepository;
        this.reviewsRepository = reviewsRepository;
    }

    ModelMapper modelMapper;
    PropertyRepository propertyRepository;
    ReviewsRepository reviewsRepository;

    public Object addReview(ReviewsDto reviewsDto, long propertyId, User user) {
        Optional<Property> property= propertyRepository.findById(propertyId);
        if(property.isEmpty()) {
            throw new RuntimeException("Property not found");
        }
        Reviews reviewStatus=reviewsRepository.findByUserAndProperty(user, property.get());
        if(reviewStatus!=null) {


            System.out.println("before map reviewsDto  " + reviewsDto.getRating());
            System.out.println("before map propertyId  " + propertyId);
            System.out.println("before map user  " + user.getUserName());
            Reviews reviews = mapToReviews(reviewsDto);
            System.out.println("service after mapToReviews " + reviews.getRating());
            reviews.setProperty(property.get());
            System.out.println("property.get() getName" + property.get().getName());
            reviews.setUser(user);
            Reviews review = reviewsRepository.save(reviews);
            ReviewsDto d = mapToReviewsDto(review);
            System.out.println("service mapToReviewsDto " + d.getUser().getUserName());
            System.out.println("service mapToReviewsDto " + d.getRating());
            System.out.println("service mapToReviewsDto " + d.getProperty().getName());
            return d;
        }
        return "review added already";
    }

    public List<ReviewsDto> getMyReviews(User user) {
        List<Reviews> li= reviewsRepository.findByUser(user);
        System.out.println();
        return li.stream().map(this::mapToReviewsDto).toList();
    }
    Reviews mapToReviews(ReviewsDto reviewsdto){
        Reviews reviews= modelMapper.map(reviewsdto,Reviews.class);
        reviews.setId(reviewsdto.getId());
        reviews.setRating(reviewsdto.getRating());
        return reviews;
    }
    ReviewsDto mapToReviewsDto(Reviews reviews){
        ReviewsDto dto= modelMapper.map(reviews,ReviewsDto.class);
        dto.setId(reviews.getId());
        dto.setRating(reviews.getRating());
        dto.setDescription(reviews.getDescription());
        return dto;
    }



}
