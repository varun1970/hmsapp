package com.hmsapp.Service;

import com.hmsapp.Entity.Property;
import com.hmsapp.Repository.PropertyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyService {
    public PropertyService(PropertyRepository propertyRepository) {
        PropertyService.propertyRepository = propertyRepository;
    }

    static PropertyRepository propertyRepository;

    public static List<Property> searchProperty(String searchparm) {
        return propertyRepository.searchProperty(searchparm);
    }
}
