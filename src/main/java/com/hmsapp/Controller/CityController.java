package com.hmsapp.Controller;

import com.hmsapp.Entity.City;
import com.hmsapp.Service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cities")
public class CityController {
    @Autowired
    private CityService cityService;


    @PostMapping
    public ResponseEntity<City> addCity(@RequestBody City city) {
        return new ResponseEntity<>(cityService.addCity(city), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<City>> getCity() {
        return new ResponseEntity<>(cityService.getCity(), HttpStatus.CREATED);
    }

}

