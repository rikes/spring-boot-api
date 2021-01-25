package br.com.rickes.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.rickes.controller.dto.CityRequest;
import br.com.rickes.controller.dto.PersonRequest;
import br.com.rickes.controller.dto.PersonResponse;
import br.com.rickes.enumerat.GenderEnum;
import br.com.rickes.model.City;
import br.com.rickes.model.Person;
import br.com.rickes.service.CityService;
import br.com.rickes.service.PersonService;



@RestController
@RequestMapping(value = "/person")
public class PersonController {
	
	@Autowired
	PersonService personService;
	
	@Autowired
	CityService cityService;

    @GetMapping("")
    public List<PersonResponse> findAll() {
    	List<Person> persons = personService.findAll();
        
    	return persons.stream()
    			.map(p -> new PersonResponse().toModel(p))
    			.collect(Collectors.toList());
    	
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonResponse> findById(@PathVariable("id") Long id) {
    	Optional<Person> person = personService.findById(id);
    	
    	if(person.isPresent()) {
    		return ResponseEntity.ok(new PersonResponse().toModel(person.get()));
    	}
    	
    	return ResponseEntity.notFound().build();
        
    }

    @PostMapping("/")
    public ResponseEntity<PersonResponse> savePerson(@RequestBody @Valid PersonRequest person) {
    	Optional<City> city = Optional.empty();
    	
    	if (person.getCity() != null) {
            city = cityService.findById(person.getCity().getId());
            
            if(city.isPresent())
            	person.setCity(new CityRequest().toModel(city.get()));
            else
            	return ResponseEntity.notFound().build();
            
        }
    	
    	Person p = new PersonRequest().fromModel(person);
        
        
        personService.savePerson(p);
        
        return new ResponseEntity<>(new PersonResponse().toModel(p), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonResponse> updatePerson(@PathVariable("id") Long id, @RequestBody PersonRequest person) {
    	Optional<Person> p = personService.findById(id);
    	
        if (p.isPresent()) {
        	
            Person personUpdate = p.get();
            if (StringUtils.hasText(person.getName()))
            	personUpdate.setName(person.getName());
            
            if (person.getAge() != null)
            	personUpdate.setAge(person.getAge());
            
            if (person.getGender() != null)
            	personUpdate.setGender(GenderEnum.fromCode(person.getGender()));
            
            if (person.getDateBirth() != null)
            	personUpdate.setDateBirth(person.getDateBirth());
            
            if (person.getCity() != null) {
            	Optional<City> city = cityService.findById(person.getCity().getId());
            	
            	if(city.isPresent()) {
            		personUpdate.setCity(city.get());
            		personService.savePerson(personUpdate);
            		return new ResponseEntity<>(new PersonResponse().toModel(personUpdate), HttpStatus.OK);
            	}else {
            		return ResponseEntity.notFound().build();
            	}
            }
            
            personService.savePerson(personUpdate);
            
            return new ResponseEntity<>(new PersonResponse().toModel(personUpdate), HttpStatus.OK);
        }
        
        return ResponseEntity.notFound().build();
        
        
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PersonResponse> findPersonByName(@PathVariable("id") Long id) {
    	
    	Optional<Person> person = personService.findById(id);
    	
    	if(person.isPresent()) {
    		this.personService.removePerson(id);
    		return ResponseEntity.ok(new PersonResponse().toModel(person.get()));
    	}
    	
    	 return ResponseEntity.notFound().build();
    }
    
    
    
    

}
