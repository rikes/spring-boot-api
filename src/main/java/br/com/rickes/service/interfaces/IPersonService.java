package br.com.rickes.service.interfaces;

import java.util.List;
import java.util.Optional;

import br.com.rickes.model.Person;

public interface IPersonService {

	public List<Person> findAll();
	public Optional<Person> findById(Long id);
	public void savePerson(Person person);
	public List<Person> getPersonByName(String name);
	public void removePerson(Long id);
	
}
