package com.hmsapp.Service;

import com.hmsapp.Entity.Property;
import com.hmsapp.Repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyService {
    @Autowired
     private PropertyRepository propertyRepository;

    public List<Property> searchProperty(String searchparm) {

         propertyRepository.searchProperty(searchparm);
         return null;
    }
}
