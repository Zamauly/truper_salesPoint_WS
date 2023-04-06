package com.truper.salespoint.api.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.truper.salespoint.api.exception.ListaCompraNotFoundException;
import com.truper.salespoint.api.model.Cliente;
import com.truper.salespoint.api.model.ListaCompra;
import com.truper.salespoint.api.repository.ListaCompraRepository;

import jakarta.transaction.Transactional;

@Service
public class ListaCompraService {
	@Autowired
	ListaCompraRepository listaCompraRepository;
	@Autowired
	ClienteService clienteService;
	
	public ArrayList<ListaCompra> getListasCompras(){
		return (ArrayList<ListaCompra>) listaCompraRepository.findAllClean();
	}
	
	public ListaCompra getListaCompra(Long id){
		ListaCompra listaCompraToSearch = listaCompraRepository.findByIdExistance(id);
		if(listaCompraToSearch == null)
			throw new ListaCompraNotFoundException(id);
		else
			return listaCompraToSearch;
		
	}
	
	public ListaCompra loadListaCompra(ListaCompra listaCompra) {
		return listaCompraRepository.save(listaCompra);
	}
	
	@Transactional
	public int deleteProducto(Long id) {
		return listaCompraRepository.logicDelete(id);
	}
	
	public ListaCompra getValuedElement(ListaCompra objectToEval) {

		if(objectToEval.getId() != null) {
			ListaCompra listaCompraToUpdate = this.getListaCompra(objectToEval.getId());
			if(listaCompraToUpdate != null) {
				objectToEval = new ServicesUtil<ListaCompra>(objectToEval).getObjectToUpdate(listaCompraToUpdate);
				objectToEval.setFechaRegistro(listaCompraToUpdate.getFechaRegistro());
			}
		}
		return objectToEval;
	}
	
	public boolean verificateClient(ListaCompra listaCompra) {
		if(listaCompra.getCliente() != null) {
			Cliente valuedClient = clienteService.getCliente(listaCompra.getCliente().getId());
			if(valuedClient == null ) 
				return false;			
			
		}
		return true;
	}
}
