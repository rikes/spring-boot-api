package br.com.rickes.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.rickes.model.City;
import br.com.rickes.repository.CityRepository;
import br.com.rickes.service.interfaces.ICityService;

@Service
public class CityService implements ICityService{

	
	@Autowired
	private CityRepository repository;
	
	public List<City> findAll(){
		return this.repository.findAll();
	}
	
	public Optional<City> findById(Long id){
		return this.repository.findById(id);
	}
	
	public void saveCity(City city){
		this.repository.save(city);
		
	}
	
	public List<City> getCityByName(String name) {
		return this.repository.findByNameContainingIgnoreCase(name);
	}
	
	public List<City> getCityByState(String state) {
		return this.repository.findByStateContainingIgnoreCase(state);
	}
	
	
}
