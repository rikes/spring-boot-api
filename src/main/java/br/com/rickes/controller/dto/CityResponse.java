package br.com.rickes.controller.dto;

import br.com.rickes.model.City;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CityResponse {

	private Long id;
	private String name;
	private String state;

    public CityResponse toModel(City c) {
    	
    	CityResponse city = null;
    	if (c != null) {
    		city = new CityResponse();
	    	city.setId(c.getId());
	    	city.setName(c.getName());
	    	city.setState(c.getState());
    	}
        return city;
    }
}
