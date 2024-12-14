package com.hmsapp.Controller;

import com.hmsapp.Entity.Property;
import com.hmsapp.Service.PropertyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/property")
public class PropertyController {
    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

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
        List<Property> properties = PropertyService.searchProperty(searchparm);
        return new ResponseEntity<>(properties, HttpStatus.OK);
    }

}
