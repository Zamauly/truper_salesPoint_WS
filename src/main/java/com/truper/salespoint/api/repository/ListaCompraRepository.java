package com.truper.salespoint.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.truper.salespoint.api.model.ListaCompra;

@Repository
public interface ListaCompraRepository extends JpaRepository<ListaCompra, Long>{

}
