package br.com.rickes.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.rickes.model.Person;
import br.com.rickes.repository.PersonRepository;
import br.com.rickes.service.interfaces.IPersonService;

@Service
public class PersonService implements IPersonService {

	
	@Autowired
	private PersonRepository repository;
	
	
	public List<Person> findAll(){
		return this.repository.findAll();
	}
	
	public Optional<Person> findById(Long id){
		return this.repository.findById(id);
	}
	
	public void savePerson(Person person){
		this.repository.save(person);
		
	}
	
	public List<Person> getPersonByName(String name) {
		return this.repository.findByNameContainingIgnoreCase(name);
	}
	
	public void removePerson(Long id){
		this.repository.deleteById(id);
	}
	
	
}
