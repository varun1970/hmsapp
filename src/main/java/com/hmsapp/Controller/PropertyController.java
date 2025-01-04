package com.hmsapp.Controller;

import com.hmsapp.Entity.Property;
import com.hmsapp.Service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/property")
public class PropertyController {


    @Autowired
    PropertyService propertyService;

    @PostMapping("/addproperty")
    public ResponseEntity<String> addProperty() {
        return new ResponseEntity<>("added property",HttpStatus.OK);
    }

    @DeleteMapping("/deleteproperty")
    public ResponseEntity<?> deleteProperty() {
        return new ResponseEntity<>("Deleted property",HttpStatus.OK);    }

    @GetMapping("/{searchparm}")
    public ResponseEntity<List<Property>> getSearchProperties(@PathVariable String searchparm) {
        List<Property> properties = propertyService.searchProperty(searchparm);
        return new ResponseEntity<>(properties, HttpStatus.OK);
    }


   // http://localhost:8080/api/v1/property/upload/file/{backetName}/property/{propertyId}
    @PostMapping("/upload/file/{backetName}/property/{propertyId}")
    public ResponseEntity<?> uplodePropertyPhotos(@RequestParam MultipartFile file,
                                                  @PathVariable String backetName,
                                                  @PathVariable long propertyId  ){
        return new ResponseEntity<>(propertyService.uplodePropertyPhotos(file, backetName, propertyId), HttpStatus.OK);
    }

    @GetMapping("/images")
    public ResponseEntity<?> getPropertyPhotos(@RequestParam long propertyId){
        return new ResponseEntity<>(propertyService.getPropertyPhotos(propertyId), HttpStatus.OK);
    }


}
