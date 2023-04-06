package com.truper.salespoint.api.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.truper.salespoint.api.exception.ListaDetalleNotFoundException;
import com.truper.salespoint.api.model.ListaDetalle;

@Repository
public interface ListaDetalleRepository extends JpaRepository<ListaDetalle, Long>{
	
	ArrayList<ListaDetalle> findAllClean();
	
	@Query("SELECT ld FROM ListaDetalle ld INNER JOIN ld.listaCompra lc INNER JOIN ld.producto p WHERE ld.id = :id AND ld.activo = true AND lc.activo = true AND p.activo = true")
	ListaDetalle findByIdAndActive(@Param("id") Long id) throws ListaDetalleNotFoundException;
	@Modifying(clearAutomatically = true)
	@Query("UPDATE ListaDetalle ld SET ld.activo = false WHERE ld.id = :id ")
	int logicDelete(@Param("id") Long id) throws ListaDetalleNotFoundException;
	
}
