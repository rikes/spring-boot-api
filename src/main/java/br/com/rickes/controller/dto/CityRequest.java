package br.com.rickes.controller.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.com.rickes.model.City;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CityRequest {

	private Long id;
	
	@NotBlank(message = "Nome Obrigatório")
	private String name;
	
	@Size(min = 2, max = 2, message = "Estado possui dois caracteres")
	@NotBlank(message = "Estado Obrigatório")
	private String state;
	
    
    public City fromModel(CityRequest c) {
    	City city = null;
    	
    	if (c != null) {
    		city = new City();
	    	city.setId(c.getId());
	    	city.setName(c.getName());
	    	city.setState(c.getState());
    	}
    	
        return city;
    }
    
    public CityRequest toModel(City c) {
    	
    	CityRequest city = null;
    	if (c != null) {
    		city = new CityRequest();
	    	city.setId(c.getId());
	    	city.setName(c.getName());
	    	city.setState(c.getState());
    	}
        return city;
    }
}
