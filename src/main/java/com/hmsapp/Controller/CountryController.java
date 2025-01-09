package com.hmsapp.Controller;

import com.hmsapp.Entity.Country;
import com.hmsapp.Service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/country")
public class CountryController {
    @Autowired
    private CountryService countryService;

    @PostMapping
    public ResponseEntity<Country> addCountry(@RequestBody Country country) {
        return new ResponseEntity(countryService.addCountry(country), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Country>> getCountry() {
        return new ResponseEntity(countryService.getCountry(), HttpStatus.CREATED);
    }


}
