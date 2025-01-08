package com.hmsapp.Service;

import com.hmsapp.Entity.Country;
import com.hmsapp.Repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {
    @Autowired
    CountryRepository countryRepository;

    public Country addCountry(Country country) {
        return countryRepository.save(country);
    }

    public List<Country> getCountry() {
        return countryRepository.findAll();
    }
}
