package org.ada.abmHibernate.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


@Entity //mapeo de un objeto con una tabla
@Table(name = "Ventas2", uniqueConstraints = {
		@UniqueConstraint(columnNames = "VentaId")})
@org.hibernate.annotations.Entity(dynamicUpdate = true)
		
public class VentaEntity implements Serializable {
	
	private static final long serialVersionUID = -1798070786993154676L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //identidad que va a generarse en la base de datos de forma secuencial
	@Column(name = "VentaId", unique = true, nullable = false) // ID corresponde al dato employeeID de abajo
	private Integer ventaId;
	
	@Column(name = "Fecha", unique = false, nullable = false)
	private Date fechaVenta;
	
	@Column(name = "Importe", unique = false, nullable = false)
	private float importe;
	
	@ManyToOne
	@JoinColumn(name = "Id_Persona", unique = false, nullable = false)
	private PersonaEntity personaEntity;

	public Integer getVentaId() {
		return ventaId;
	}

	public void setVentaId(Integer ventaId) {
		this.ventaId = ventaId;
	}

	public Date getFechaVenta() {
		return fechaVenta;
	}

	public void setFechaVenta(Date fechaVenta) {
		this.fechaVenta = fechaVenta;
	}

	public float getImporte() {
		return importe;
	}

	public void setImporte(float importe) {
		this.importe = importe;
	}

	public PersonaEntity getPersonaEntity() {
		return personaEntity;
	}

	public void setPersonaEntity(PersonaEntity personaEntity) {
		this.personaEntity = personaEntity;
	}


}