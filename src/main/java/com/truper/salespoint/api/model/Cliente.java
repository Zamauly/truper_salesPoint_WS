package com.truper.salespoint.api.model;

import java.util.function.Supplier;

//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

//@JsonIgnoreProperties({"fechaActuliza","fechaRegistro"})
@Entity
@Table(name = "cliente")
@NamedQuery(name = "Cliente.findAllClean", query = "SELECT c FROM Cliente c WHERE c.activo = true")
public class Cliente extends ParentModel {

	private static final String value = null;

	@Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column(unique = true, name = "id", nullable = false)
	protected Long id;
    
    @Column(name = "nombre", length = 50, nullable = false)
    @NotBlank(message = "nombre is required")
    @Size(min = 3, max = 49, message = "nombre length must be between 3 and 49")
    protected String nombre;
   
	@Column(name = "prioridad_id", length = 4, nullable = false)
	@Digits(fraction = 0, integer = 1, message = "prioridadId  must be integer or parseable")
	@Min(value = 1,message="prioridadId must be at less 1")
	@Max(value = 6,message="prioridadId must be at max 6")
	@Nullable
	protected int prioridadId;
    
    public Cliente() {}
    
	public Cliente( String nombre, int prioridadId) {
		super();
		this.nombre = nombre;
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

	public int getPrioridadId() {
		return prioridadId;
	}

	public void setPrioridadId(int prioridadId) {
		this.prioridadId = prioridadId;
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
