package com.truper.salespoint.api.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "lista_compra")
@NamedQuery(name = "ListaCompra.findAllClean", query = "SELECT lc FROM ListaCompra lc INNER JOIN lc.cliente c WHERE lc.activo = true AND c.activo = true")
public class ListaCompra {

	@Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column(unique = true, name = "id", nullable = false)
	protected Long id;
	
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name="cliente_id")
    @JsonProperty(access = Access.READ_WRITE)
    @Embedded
    private Cliente cliente;
    
    @Column(name = "nombre", length = 50, nullable = true)
    protected String nombre;
    
    @Column(name = "consideraciones", length = 150, nullable = false)
    protected String consideraciones;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_registro", nullable = false)
    protected Date fechaRegistro;  

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_actualiza", nullable = false)
    protected Date fechaActuliza;
    
	@Column(name = "activo", nullable = false)
	protected boolean activo;

	public ListaCompra() {}

	public ListaCompra(Cliente cliente, String nombre, String consideraciones, Date fechaRegistro, Date fechaActuliza,
			boolean activo) {
		super();
		this.cliente = cliente;
		this.nombre = nombre;
		this.consideraciones = consideraciones;
		this.fechaRegistro = fechaRegistro;
		this.fechaActuliza = fechaActuliza;
		this.activo = activo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getConsideraciones() {
		return consideraciones;
	}

	public void setConsideraciones(String consideraciones) {
		this.consideraciones = consideraciones;
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

	public boolean getActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	@Override
	public String toString() {
		return "ListaCompra [id=" + id + ", cliente=" + cliente + ", nombre=" + nombre + ", consideraciones="
				+ consideraciones + ", fechaRegistro=" + fechaRegistro + ", fechaActuliza=" + fechaActuliza
				+ ", activo=" + activo + "]";
	}

}
