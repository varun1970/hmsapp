package com.hmsapp.PayLoad;

import com.hmsapp.Entity.Reviews;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
public class ReviewsDto extends Reviews {
    private Long id;
    private int rating;
    private String description;


}
