package com.truper.salespoint.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.truper.salespoint.api.model.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long>{

}
