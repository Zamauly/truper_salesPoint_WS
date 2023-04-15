package com.truper.salespoint.api.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.truper.salespoint.api.exception.NotFoundException;
import com.truper.salespoint.api.model.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long>{
	
	ArrayList<Producto> findAllClean();
	
	@Query("SELECT p FROM Producto p WHERE p.id = :id AND p.activo = true ")
	Producto findByIdExistance(@Param("id") Long id) throws NotFoundException;
	
	@Modifying(clearAutomatically = true)
	@Query("UPDATE Producto p SET p.activo = false WHERE p.id = :id ")
	int logicDelete(@Param("id") Long id) throws NotFoundException;
	
}
