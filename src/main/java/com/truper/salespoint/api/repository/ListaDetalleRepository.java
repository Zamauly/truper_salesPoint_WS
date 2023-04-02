package com.truper.salespoint.api.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.truper.salespoint.api.model.ListaDetalle;

@Repository
public interface ListaDetalleRepository extends JpaRepository<ListaDetalle, Long>{
	ArrayList<ListaDetalle> findAllClean();
}
