package com.truper.salespoint.api.repository.system;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.truper.salespoint.api.commons.Constants.ValidRole;
import com.truper.salespoint.api.model.system.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	
	Optional<Role> findByRol(ValidRole rol);
}
