package org.ada.test.abmHibernate.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


@Entity //mapeo de un objeto con una tabla
@Table(name = "Persona", uniqueConstraints = {
		@UniqueConstraint(columnNames = "ID")})
@org.hibernate.annotations.Entity(dynamicUpdate = true)
		
public class PersonaEntity implements Serializable {
	
	private static final long serialVersionUID = -1798070786993154676L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //identidad que va a generarse en la base de datos de forma secuencial
	@Column(name = "ID", unique = true, nullable = false) 
	private Integer personaId;
	
	@Column(name = "NOMBRE", unique = true, nullable = false, length = 100)
	private String name;
	
	@Column(name = "EDAD", unique = false, nullable = false, length = 100)
	private int edad;
	
	@Column(name = "FECHA_NACIMIENTO", unique = false, nullable = false, length = 100)
	private String fNac;

	public Integer getPersonaId() {
		return personaId;
	}

	public void setPersonaId(Integer personaId) {
		this.personaId = personaId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getfNac() {
		return fNac;
	}

	public void setfNac(String fNac) {
		this.fNac = fNac;
	}

}