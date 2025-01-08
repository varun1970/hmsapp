package com.hmsapp.PayLoad;

import com.hmsapp.Entity.Property;
import com.hmsapp.Entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewsDto  {
    private Long id;
    private int rating;
    private String description;
    private User user;
    private Property property;
}
