package com.truper.salespoint.api.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.truper.salespoint.api.exception.ListaCompraNotFoundException;
import com.truper.salespoint.api.model.ListaCompra;

@Repository
public interface ListaCompraRepository extends JpaRepository<ListaCompra, Long>{
	
	ArrayList<ListaCompra> findAllClean();
	
	@Query("SELECT lc FROM ListaCompra lc INNER JOIN lc.cliente c WHERE lc.id = :id AND lc.activo = true AND c.activo = true")
	ListaCompra findByIdExistance(@Param("id") Long id) throws ListaCompraNotFoundException;
}
