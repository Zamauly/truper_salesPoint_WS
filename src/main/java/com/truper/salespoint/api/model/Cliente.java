package com.truper.salespoint.api.model;

import java.util.Date;
import java.util.function.Supplier;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "cliente")
@NamedQuery(name = "Cliente.findAllClean", query = "SELECT c FROM Cliente c WHERE c.activo = true")
public class Cliente {

	private static final String value = null;

	@Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column(unique = true, name = "id", nullable = false)
	protected Long id;
    
    @Column(name = "nombre", length = 50, nullable = false)
    protected String nombre;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_registro", nullable = false)
    protected Date fechaRegistro;  

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_actualiza", nullable = false)
    protected Date fechaActuliza;
    
	@Column(name = "prioridad_id", length = 4, nullable = false)
	protected int prioridadId;
	
	@Column(name = "activo", nullable = false)
	protected boolean activo;
    
    public Cliente() {}
    
	public Cliente( String nombre, Date fechaRegistro, Date fechaActuliza, int prioridadId, boolean activo) {
		super();
		this.nombre = nombre;
		this.fechaRegistro = fechaRegistro;
		this.fechaActuliza = fechaActuliza;
		this.prioridadId = prioridadId;
		this.activo = activo;	
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

	public boolean getActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}
    
    @Override
	public String toString() {
		return "Cliente [ id= " +this.id +", nombre= " + this.nombre + ", fechaRegistro= " + fechaRegistro +", fechaActuliza= "
				+ fechaActuliza +", prioridadId= " + prioridadId + ", activo=" + activo +" ]";
	}
    public <X extends Throwable> Object orElseThrow(Supplier<? extends X> exceptionSupplier) throws X {
        if (value != null) {
            return value;
        } else {
            throw exceptionSupplier.get();
        }
    }
    public Cliente isActivo() {
    	if(this.activo)
    		return this;
    	else return null;
    				
    }
}
