package com.truper.salespoint.api.model.system;

import com.truper.salespoint.api.commons.Constants.ValidRole;
import com.truper.salespoint.api.model.ParentModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "roles")
public class Role extends ParentModel {
	@Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column(unique = true, name = "id", nullable = false)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "rol",length = 20)
	private ValidRole rol;

	public Role() {
		super();
	}

	public Role(ValidRole rol) {
		super();
		this.rol = rol;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ValidRole getRol() {
		return rol;
	}

	public void setRol(ValidRole rol) {
		this.rol = rol;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", rol=" + rol + "]";
	}
	
}
