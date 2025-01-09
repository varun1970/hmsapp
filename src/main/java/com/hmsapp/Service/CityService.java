package com.hmsapp.Service;

import com.hmsapp.Entity.City;
import com.hmsapp.Repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {
    @Autowired
    public CityRepository cityRepository;


    public City addCity(City city) {
        return cityRepository.save(city);
    }

    public List<City> getCity() {
        return cityRepository.findAll();
    }
}
