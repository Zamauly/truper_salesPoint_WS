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
@Table(name = "lista_detalle")
@NamedQuery(name = "ListaDetalle.findAllClean", query = "SELECT ld FROM ListaDetalle ld WHERE ld.activo = true")
public class ListaDetalle {

	@Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column(unique = true, name = "id", nullable = false)
	protected Long id;
	
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name="lista_compra_id", nullable = false)
    @JsonProperty(access = Access.READ_WRITE)
    @Embedded
    protected ListaCompra listaCompra;
    
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "producto_id", nullable = false)
    @JsonProperty(access = Access.READ_WRITE)
    @Embedded
    protected Producto producto;
    
    @Column(name = "cantidad", length = 8, nullable = false)
    protected int cantidad;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_registro", nullable = false)
    protected Date fechaRegistro;  

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_actualiza", nullable = false)
    protected Date fechaActuliza;
    
	@Column(name = "activo", nullable = false)
	protected boolean activo;

	public ListaDetalle() {}

	public ListaDetalle(ListaCompra listaCompra, Producto producto, int cantidad, Date fechaRegistro,
			Date fechaActuliza, boolean activo) {
		super();
		this.listaCompra = listaCompra;
		this.producto = producto;
		this.cantidad = cantidad;
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

	public ListaCompra getListaCompra() {
		return listaCompra;
	}

	public void setListaCompra(ListaCompra listaCompra) {
		this.listaCompra = listaCompra;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
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
		return "ListaDetalle [id=" + id + ", listaCompra=" + listaCompra + ", producto=" + producto + ", cantidad="
				+ cantidad + ", fechaRegistro=" + fechaRegistro + ", fechaActuliza=" + fechaActuliza + ", activo="
				+ activo + "]";
	}
	
}
