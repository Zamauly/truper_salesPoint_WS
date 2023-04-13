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
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

//@JsonIgnoreProperties({"fechaActuliza","fechaRegistro"})
@Entity
@Table(name = "lista_detalle")
@NamedQuery(name = "ListaDetalle.findAllClean", query = "SELECT ld FROM ListaDetalle ld INNER JOIN ld.listaCompra lc INNER JOIN ld.producto p WHERE ld.activo = true AND lc.activo = true AND p.activo = true")
public class ListaDetalle extends ParentModel {

	@Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column(unique = true, name = "id", nullable = false)
	protected Long id;
	
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name="lista_compra_id", nullable = false)
    @JsonProperty(access = Access.READ_WRITE)
    @Embedded
    @NotNull(message = "listaCompra.id is required")
    protected ListaCompra listaCompra;
    
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "producto_id", nullable = false)
    @JsonProperty(access = Access.READ_WRITE)
    @Embedded
    @NotNull(message = "producto.id is required")
    protected Producto producto;
    
    @Column(name = "cantidad", length = 6, nullable = false)
	@Digits(fraction = 0, integer = 4, message = "cantidad  must be integer or parseable")
	@Min(value = 0,message="cantidad must be at less 0")
	@Max(value = 9999,message="cantidad must be at max 999")
    @NotNull(message = "cantidad is required")
    protected int cantidad;
    
    public ListaDetalle() {}

	public ListaDetalle(ListaCompra listaCompra, Producto producto, int cantidad ) {
		super();
		this.listaCompra = listaCompra;
		this.producto = producto;
		this.cantidad = cantidad;
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
