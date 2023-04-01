package com.truper.salespoint.api.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "cliente")
public class Cliente {

	@Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column(unique = true, name = "Id", nullable = false)
	protected Long id;
    
    @Column(name = "nombre", length = 64, nullable = false)
    protected String nombre;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_registro", nullable = false)
    protected Date fechaRegistro;  

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_actualiza", nullable = false)
    protected Date fechaActuliza;
    
	@Column(name = "prioridad_id", length = 64, nullable = false)
    private int prioridadId;

    public Cliente() {}
    
	public Cliente( String nombre, Date fechaRegistro, Date fechaActuliza, int prioridadId) {
		super();
		this.nombre = nombre;
		this.fechaRegistro = fechaRegistro;
		this.fechaActuliza = fechaActuliza;
		this.prioridadId = prioridadId;		
    }
	
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public Date getFechaActuliza() {
		return fechaActuliza;
	}

	public void setFechaActuliza(Date fechaActuliza) {
		this.fechaActuliza = fechaActuliza;
	}

	public int getPrioridadId() {
		return prioridadId;
	}

	public void setPrioridadId(int prioridadId) {
		this.prioridadId = prioridadId;
	}

	public Cliente(int prioridadId) {
		super();
		this.prioridadId = prioridadId;
	}
    
    @Override
	public String toString() {
		return "Cliente [ id= " +this.id +", nombre= " + this.nombre + ", fechaRegistro= " + fechaRegistro +", fechaActuliza= "
				+ fechaActuliza +", prioridadId= " + prioridadId + " ]";
	}
    
}
