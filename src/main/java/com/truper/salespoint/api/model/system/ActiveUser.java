package com.truper.salespoint.api.model.system;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.truper.salespoint.api.model.ParentModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@JsonIgnoreProperties({"password","roles"})
@Entity
@Table(	name = "active_users",  uniqueConstraints = { 
		@UniqueConstraint(columnNames = "user_name"),
		@UniqueConstraint(columnNames = "email") })
public class ActiveUser extends ParentModel {

	@Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column(unique = true, name = "id", nullable = false)
	private Long id;

	@Column(name = "user_name", length = 15, nullable = false, unique = true)
	@NotBlank(message = "username is required")
    @Size(min = 3, max = 49, message = "username length must be between 3 and 15")
	private String username;

	@NotBlank
	@Size(min = 6, max = 50,  message = "email length must be between 6 and 50")
	@Email(message = "email length must be between 6 and 50")
    @Column(unique = true, name = "email", nullable = false)
	private String email;

	@NotBlank
	@Size(min = 8, max = 150,  message = "password length must be between 8  and 50")
    @Column(name = "password", nullable = false)
	private String password;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "user_roles", 
				joinColumns = @JoinColumn(name = "user_id"), 
				inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();
	
	public ActiveUser() {
		super();
	}

	public ActiveUser(	@NotBlank(message = "username is required") @Size(min = 3, max = 49, message = "username length must be between 3 and 15") String username,
			@NotBlank @Size(min = 6, max = 50, message = "email length must be between 6 and 50") @Email(message = "email length must be between 6 and 50") String email) {
		super();
		this.username = username;
		this.email = email;
	}

	public ActiveUser(	@NotBlank(message = "username is required") @Size(min = 3, max = 49, message = "username length must be between 3 and 15") String username,
			@NotBlank @Size(min = 6, max = 50, message = "email length must be between 6 and 50") @Email(message = "email length must be between 6 and 50") String email,
			@NotBlank @Size(min = 8, max = 50, message = "password length must be between 8  and 50") String password) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "ServiceUser [id=" + id + ", username=" + username + ", email=" + email + "]";
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
}
