package com.hmsapp.Repository;

import com.hmsapp.Entity.Property;
import com.hmsapp.Entity.Reviews;
import com.hmsapp.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewsRepository extends JpaRepository<Reviews, Long> {
     List<Reviews> findByUser(User user);
     Reviews findByUserAndProperty(User user, Property property);
}