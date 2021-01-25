package br.com.rickes.controller.dto;

import java.time.LocalDate;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.rickes.enumerat.GenderEnum;
import br.com.rickes.model.Person;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonRequest {

	@NotBlank(message = "Nome Obrigatório")
	private String name;
    
	@Min(value = 0, message = "Sexo deverá ser 0(Masculino) ou 1(Femino)")
	@Max(value = 1, message = "Sexo deverá ser 0(Masculino) ou 1(Femino)")
	@NotNull(message = "Gênero Obrigatório")
	private Integer gender;
	
	@NotNull(message = "Data de nascimento Obrigatório")
    private LocalDate dateBirth;
	
	@Min(value = 0, message = "Idade deve ser maior do que 0")
	@NotNull(message = "Idade Obrigatório")
    private Integer age;
	
    private CityRequest city;
    
	public Person fromModel(PersonRequest p) {
		Person person = null;

		if (p != null) {
			person = new Person();
			person.setName(p.getName());
			person.setAge(p.getAge());
			person.setGender(GenderEnum.fromCode(p.getGender()));
			person.setDateBirth(p.getDateBirth());
			person.setCity(new CityRequest().fromModel(p.getCity()));

		}
		return person;
	}

}
