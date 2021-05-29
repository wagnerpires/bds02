package com.devsuperior.bds02.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.bds02.dto.CityDTO;
import com.devsuperior.bds02.entities.City;
import com.devsuperior.bds02.repositories.CityRepository;
import com.devsuperior.bds02.services.exceptions.DatabaseException;
import com.devsuperior.bds02.services.exceptions.ResourceNotFoundException;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    @Transactional(readOnly = true)
    public List<CityDTO> findAll() {
        List<City> list = cityRepository.findAll(Sort.by("name"));
        
        return list.stream().map(x -> new CityDTO(x)).collect(Collectors.toList()) ;

    }

    public void deleteById(Long id) {
        try {
            cityRepository.deleteById(id);
            
        } catch (EmptyResultDataAccessException ex) {
            throw new ResourceNotFoundException(id);
            
        } catch (DataIntegrityViolationException ex) {
            throw new DatabaseException("Database Integrity Violation Exception ID " + id);
            
        }
    }

}