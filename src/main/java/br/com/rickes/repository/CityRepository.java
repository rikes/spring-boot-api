package br.com.rickes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.rickes.model.City;

@Repository
public interface CityRepository extends JpaRepository<City, Long >{
    
    List<City> findByNameContainingIgnoreCase(String name);
    
    List<City> findByStateContainingIgnoreCase(String state);

}