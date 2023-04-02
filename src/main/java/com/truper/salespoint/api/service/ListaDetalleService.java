package com.truper.salespoint.api.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.truper.salespoint.api.exception.ListaDetalleNotFoundException;
import com.truper.salespoint.api.model.ListaDetalle;
import com.truper.salespoint.api.repository.ListaDetalleRepository;

@Service
public class ListaDetalleService {
	@Autowired
	ListaDetalleRepository listaDetalleRepository;
	
	public ArrayList<ListaDetalle> getListasDetalle(){
		return (ArrayList<ListaDetalle>) listaDetalleRepository.findAll();
	}

	public ListaDetalle getListaDetalle(Long id){
		return listaDetalleRepository.findById(id).orElseThrow(() -> new ListaDetalleNotFoundException(id));
	}
	
	public ListaDetalle loadListaDetalle(ListaDetalle listaDetalle) {
		
		return listaDetalleRepository.save(listaDetalle);
	}
	
}
