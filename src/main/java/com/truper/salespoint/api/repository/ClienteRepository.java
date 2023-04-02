package com.truper.salespoint.api.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.truper.salespoint.api.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	
	ArrayList<Cliente> findAllClean();
}
