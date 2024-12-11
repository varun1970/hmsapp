package com.hmsapp.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/property")
public class PropertyController {

    @PostMapping("/addproperty")
    public ResponseEntity<String> addProperty() {
        return new ResponseEntity<>("added property",HttpStatus.OK);
    }

    @DeleteMapping("/deleteproperty")
    public ResponseEntity<?> deleteProperty() {
        return new ResponseEntity<>("Deleted property",HttpStatus.OK);    }

}
