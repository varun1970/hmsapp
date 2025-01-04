package com.hmsapp.Service;

import com.hmsapp.Entity.Property;
import com.hmsapp.Entity.PropertyImage;
import com.hmsapp.Repository.PropertyImageRepository;
import com.hmsapp.Repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class PropertyService {
    @Autowired
     private PropertyRepository propertyRepository;
    @Autowired
    BucketService bucketService;

    @Autowired
    PropertyImageRepository propertyImageRepository;

    public List<Property> searchProperty(String searchparm) {
        return   propertyRepository.searchProperty(searchparm);
    }

    public Object uplodePropertyPhotos(MultipartFile file, String backetName, Long propertyId) {
        Property property = propertyRepository.findById(propertyId).orElseThrow(()->new IllegalStateException("Property with id: " + propertyId + " not found"));
        String imageUrl= String.valueOf(bucketService.uploadFile(file, backetName));
        PropertyImage propertyImage = new PropertyImage();
        propertyImage.setUrl(imageUrl);
        propertyImage.setProperty(property);
        return propertyImageRepository.save(propertyImage);
    }

    public Object getPropertyPhotos(long propertyId) {
        Property property=propertyRepository.findById(propertyId).orElseThrow(()->new IllegalStateException( "Property with id: " + propertyId + " not found"));
       return propertyImageRepository.findByProperty(property);
    }
}
