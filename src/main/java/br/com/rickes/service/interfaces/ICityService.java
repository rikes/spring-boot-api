package br.com.rickes.service.interfaces;

import java.util.List;
import java.util.Optional;

import br.com.rickes.model.City;

public interface ICityService {

	public List<City> findAll();
	public Optional<City> findById(Long id);
	public void saveCity(City city);
	public List<City> getCityByName(String name);
	public List<City> getCityByState(String state);
	
}
