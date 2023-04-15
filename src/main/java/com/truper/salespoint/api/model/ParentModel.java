package com.truper.salespoint.api.model;

import java.util.Date;

import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@MappedSuperclass
public class ParentModel {

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_registro", nullable = true)
	@Nullable
	protected Date fechaRegistro;

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_actualiza", nullable = true)
	@Nullable
	protected Date fechaActuliza;

	@Column(name = "activo", nullable = false)
	protected boolean activo;

	@PrePersist
	protected void onCreate() {
		fechaActuliza = fechaRegistro = new Date();
		activo = true;
	}

	@PreUpdate
	protected void onUpdate() {
		fechaActuliza = new Date();
	}

	public boolean getActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public void setFechaActuliza(Date fechaActuliza) {
		this.fechaActuliza = fechaActuliza;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public Date getFechaActuliza() {
		return fechaActuliza;
	}

}
