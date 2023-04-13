package com.truper.salespoint.api.repository.system;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.truper.salespoint.api.model.system.ActiveUser;

@Repository
public interface ActiveUserRepository extends JpaRepository<ActiveUser, Long> {
	Optional<ActiveUser> findByUsername(String username);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
}
