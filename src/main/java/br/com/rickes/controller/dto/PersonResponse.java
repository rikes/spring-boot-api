package br.com.rickes.controller.dto;

import java.time.LocalDate;

import br.com.rickes.model.Person;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PersonResponse {

	
	private Long id;
	private String name;
    private String gender;
    private LocalDate dateBirth;
    private int age;
    private CityResponse city;

    public PersonResponse toModel(Person p) {
    	PersonResponse person = null;
    	
    	if(p != null) {
    		person = new PersonResponse();
	    	person.setId(p.getId());
	    	person.setName(p.getName());
	    	person.setAge(p.getAge());
	    	person.setGender(p.getGender().name());
	    	person.setDateBirth(p.getDateBirth());
	    	person.setCity(new CityResponse().toModel(p.getCity()));
        }	
        return person;
    }
}
