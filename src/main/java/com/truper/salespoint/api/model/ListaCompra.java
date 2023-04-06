package com.truper.salespoint.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.annotation.Nullable;
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
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

//@JsonIgnoreProperties({"fechaActuliza","fechaRegistro"})
@Entity
@Table(name = "lista_compra")
@NamedQuery(name = "ListaCompra.findAllClean", query = "SELECT ls FROM ListaCompra ls INNER JOIN ls.cliente c WHERE ls.activo = true AND c.activo = true")
public class ListaCompra extends ParentModel {

	@Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column(unique = true, name = "id", nullable = false)
	protected Long id;
	
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name="cliente_id")
    @JsonProperty(access = Access.READ_WRITE)
    @Embedded
    @NotNull(message = "cliente.id is required")
    private Cliente cliente;
    
    @Column(name = "nombre", length = 50, nullable = true)
    @NotBlank(message = "nombre is required")
    @Size(min = 3, max = 31, message = "nombre must be string length must be between 3 and 31")
    protected String nombre;
    
    @Column(name = "consideraciones", length = 150, nullable = false)
	@Nullable
    protected String consideraciones;

	public ListaCompra() {}

	public ListaCompra(Cliente cliente, String nombre, String consideraciones) {
		super();
		this.cliente = cliente;
		this.nombre = nombre;
		this.consideraciones = consideraciones;
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

	@Override
	public String toString() {
		return "ListaCompra [id=" + id + ", cliente=" + cliente + ", nombre=" + nombre + ", consideraciones="
				+ consideraciones + ", fechaRegistro=" + fechaRegistro + ", fechaActuliza=" + fechaActuliza
				+ ", activo=" + activo + "]";
	}

}
