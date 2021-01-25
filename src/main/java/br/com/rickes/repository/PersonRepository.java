package br.com.rickes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.rickes.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long >{
    
    List<Person> findByNameContainingIgnoreCase(String name);

}