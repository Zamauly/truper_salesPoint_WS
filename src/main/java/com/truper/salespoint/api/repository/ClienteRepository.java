package com.truper.salespoint.api.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.truper.salespoint.api.exception.NotFoundException;
import com.truper.salespoint.api.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	
	ArrayList<Cliente> findAllClean();
	
	@Query("SELECT c FROM Cliente c WHERE c.id = :id AND c.activo = true ")
	Cliente findByIdExistance(@Param("id") Long id) throws NotFoundException;
	
	@Modifying(clearAutomatically = true)
	@Query("UPDATE Cliente c SET c.activo = false WHERE c.id = :id ")
	int logicDelete(@Param("id") Long id) throws NotFoundException;
	
}
