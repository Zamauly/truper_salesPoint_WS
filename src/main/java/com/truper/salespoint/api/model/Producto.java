package com.truper.salespoint.api.model;

//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import com.truper.salespoint.api.exception.InventaryException;

//@JsonIgnoreProperties({"fechaActuliza","fechaRegistro"})
@Entity
@Table(name = "productos")
@NamedQuery(name = "Producto.findAllClean", query = "SELECT p FROM Producto p WHERE p.activo = true AND p.existencia > 0")
public class Producto extends ParentModel {

	@Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column(unique = true, name = "id", nullable = false)
	protected Long id;
	   
	@Column(name = "clave", length = 15, nullable = false, unique = true)
	@NotBlank(message = "clave is required")
    @Size(min = 3, max = 49, message = "nombre length must be between 3 and 15")
    protected String clave;
    
    @Column(name = "descripcion", length = 150, nullable = false)
    @Size(min = 3, max = 49, message = "descripcion length must be between 3 and 15")
	@Nullable
    protected String descripcion;
    
    @Column(name = "nombre", length = 32, nullable = true, unique = true)
    @NotBlank(message = "nombre is required")
    @Size(min = 3, max = 31, message = "nombre must be string length must be between 3 and 31")
    protected String nombre;

    @Column(name = "existencia", length = 6, nullable = false)
	@Digits(fraction = 0, integer = 4, message = "existencia  must be integer or parseable")
	@Min(value = 0,message="existencia must be at less 0")
	@Max(value = 9999,message="existencia must be at max 999")
    protected int existencia;
    
	public Producto() {}
	
	public Producto(String clave, String descripcion, String nombre, int existencia) {
		super();
		this.clave = clave;
		this.descripcion = descripcion;
		this.nombre = nombre;
		this.existencia = existencia;
	}

	public boolean inventaryOperation(int adquiredQty) {
		boolean operationResult = false;
		if(adquiredQty > existencia) 
			throw new InventaryException(nombre, existencia);		
		else {
			existencia = existencia -adquiredQty;
			operationResult = true;
		}
		return operationResult;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getExistencia() {
		return existencia;
	}

	public void setExistencia(int existencia) {
		this.existencia = existencia;
	}

	@Override
	public String toString() {
		return "Producto [id=" + id + ", clave=" + clave + ", descripcion=" + descripcion + ", nombre=" + nombre
				+ ", fechaRegistro=" + fechaRegistro + ", fechaActuliza=" + fechaActuliza + ", existencia=" + existencia
				+ ", activo=" + activo + "]";
	}
	
}
