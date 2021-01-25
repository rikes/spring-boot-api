package br.com.rickes.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.rickes.controller.dto.CityRequest;
import br.com.rickes.controller.dto.CityResponse;
import br.com.rickes.model.City;
import br.com.rickes.service.CityService;


@RestController
@RequestMapping(value = "/city")
public class CityController {
	
	@Autowired
	CityService cityService;

    @GetMapping("")
    public List<CityResponse> findAll() {
    	List<City> citys = cityService.findAll();
        
    	return citys.stream()
    			.map(p -> new CityResponse().toModel(p))
    			.collect(Collectors.toList());
    	
    }

    @GetMapping("/{id}")
    public ResponseEntity<CityResponse> findById(@PathVariable("id") Long id) {
    	Optional<City> city = cityService.findById(id);
    	
    	if(city.isPresent()) {
    		return ResponseEntity.ok(new CityResponse().toModel(city.get()));
    	}
    	
    	return ResponseEntity.notFound().build();
        
    }

    @PostMapping("/")
    public ResponseEntity<CityResponse> saveCity(@RequestBody @Valid CityRequest city) {
        City p = new CityRequest().fromModel(city);

        cityService.saveCity(p);
        
        return new ResponseEntity<>(new CityResponse().toModel(p), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CityResponse> updateCity(@PathVariable("id") Long id, @RequestBody CityRequest city) {
    	Optional<City> p = cityService.findById(id);
    	

        if (p.isPresent()) {
            City cityUpdate = p.get();
            
            if (StringUtils.hasText(city.getName()))
            	cityUpdate.setName(city.getName());
          
            if (StringUtils.hasText(city.getState()))
            	cityUpdate.setState(city.getState());

            cityService.saveCity(cityUpdate);
            
            return new ResponseEntity<>(new CityResponse().toModel(cityUpdate), HttpStatus.OK);
        }
        
        return ResponseEntity.notFound().build();
        
        
    }

    @GetMapping("{name}/name")
    public List<CityResponse> findCityByName(@PathVariable("name") String name) {
    	
    	List<City> citys = cityService.getCityByName(name);
    	
    	return citys.stream()
    			.map(p -> new CityResponse().toModel(p))
    			.collect(Collectors.toList());
    	
    }
    
    @GetMapping("{state}/state")
    public List<CityResponse> findCityByState(@PathVariable("state") String state) {
    	
    	List<City> citys = cityService.getCityByState(state);
    	
    	return citys.stream()
    			.map(p -> new CityResponse().toModel(p))
    			.collect(Collectors.toList());
    	
    }
    
    

}
