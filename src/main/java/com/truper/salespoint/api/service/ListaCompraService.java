package com.truper.salespoint.api.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.truper.salespoint.api.exception.ListaCompraNotFoundException;
import com.truper.salespoint.api.model.ListaCompra;
import com.truper.salespoint.api.repository.ListaCompraRepository;

@Service
public class ListaCompraService {
	@Autowired
	ListaCompraRepository listaCompraRepository;
	
	public ArrayList<ListaCompra> getListasCompras(){
		return (ArrayList<ListaCompra>) listaCompraRepository.findAllClean();
	}
	
	public ListaCompra getListaCompra(Long id){
		return listaCompraRepository.findById(id).orElseThrow(() -> new ListaCompraNotFoundException(id));
	}
	
	public ListaCompra loadListaCompra(ListaCompra listaCompra) {
		return listaCompraRepository.save(listaCompra);
	}
	
}
