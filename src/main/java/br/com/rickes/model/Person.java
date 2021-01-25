package br.com.rickes.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.rickes.enumerat.GenderEnum;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "person")
public class Person implements Serializable{

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_person")
    @JsonIgnore
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="gender", length = 1)
    @Enumerated(EnumType.ORDINAL)
    private GenderEnum gender;
    
    @Column(name="date_birth")
    private LocalDate dateBirth;
    
    @Column(name="age")
    private int age;
    
    @JoinColumn(name = "id_city")
    @ManyToOne
    private City city;

}