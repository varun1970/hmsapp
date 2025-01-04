package com.hmsapp.Repository;

import com.hmsapp.Entity.Property;
import com.hmsapp.Entity.PropertyImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PropertyImageRepository extends JpaRepository<PropertyImage, Long> {
    List<PropertyImage> findByProperty(Property property);
}