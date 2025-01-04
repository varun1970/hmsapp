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
import java.util.stream.Collectors;

@Service
public class ReviewsService {
    public ReviewsService(ModelMapper modelMapper, PropertyRepository propertyRepository, ReviewsRepository reviewsRepository) {
        this.modelMapper = modelMapper;
        this.propertyRepository = propertyRepository;
        this.reviewsRepository = reviewsRepository;
    }

    private ModelMapper modelMapper;
    private PropertyRepository propertyRepository;
    private ReviewsRepository reviewsRepository;

    public Object addReview(ReviewsDto reviewsDto, long propertyId, User user) {
        Optional<Property> property = propertyRepository.findById(propertyId);
        if (property.isEmpty()) {
            throw new RuntimeException("Property not found");
        }
        Reviews reviewStatus = reviewsRepository.findByUserAndProperty(user, property.get());
        if (reviewStatus == null) {
            Reviews reviews = modelMapper.map(reviewsDto,Reviews.class);
            reviews.setProperty(property.get());
            reviews.setUser(user);
            Reviews r = reviewsRepository.save(reviews);
            return modelMapper.map(r,ReviewsDto.class);
        } else
            return "review added already";
    }

    public List<ReviewsDto> getMyReviews(User user) {
        List<Reviews> li = reviewsRepository.findByUser(user);
        List<ReviewsDto> reviewsDTO = li.stream()
                .map(reviews1 -> {
                    reviews1.getUser().setPassword(null);
                    return modelMapper.map(reviews1, ReviewsDto.class);
                })
                .collect(Collectors.toList());
        return reviewsDTO;
    }
}
