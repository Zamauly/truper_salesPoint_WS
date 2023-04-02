package com.truper.salespoint.api.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "productos", uniqueConstraints = {@UniqueConstraint(columnNames  = {"clave"})})
@NamedQuery(name = "Producto.findAllClean", query = "SELECT p FROM Producto p WHERE p.activo = true")
public class Producto {

	@Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column(unique = true, name = "id", nullable = false)
	protected Long id;
	   
	@Column(name = "clave", length = 15, nullable = false)
    protected String clave;
    
    @Column(name = "descripcion", length = 150, nullable = false)
    protected String descripcion;
    
    @Column(name = "nombre", length = 32, nullable = true)
    protected String nombre;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_registro", nullable = false)
    protected Date fechaRegistro;  

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_actualiza", nullable = false)
    protected Date fechaActuliza;
    
    @Column(name = "existencia", length = 8, nullable = false)
    protected int existencia;
    
	@Column(name = "activo", nullable = false)
	protected boolean activo;

	public Producto() {}
	
	public Producto(String clave, String descripcion, String nombre, Date fechaRegistro, Date fechaActuliza,
			int existencia, boolean activo) {
		super();
		this.clave = clave;
		this.descripcion = descripcion;
		this.nombre = nombre;
		this.fechaRegistro = fechaRegistro;
		this.fechaActuliza = fechaActuliza;
		this.existencia = existencia;
		this.activo = activo;
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

	public int getExistencia() {
		return existencia;
	}

	public void setExistencia(int existencia) {
		this.existencia = existencia;
	}

	public boolean getActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	@Override
	public String toString() {
		return "Producto [id=" + id + ", clave=" + clave + ", descripcion=" + descripcion + ", nombre=" + nombre
				+ ", fechaRegistro=" + fechaRegistro + ", fechaActuliza=" + fechaActuliza + ", existencia=" + existencia
				+ ", activo=" + activo + "]";
	}
    
}
