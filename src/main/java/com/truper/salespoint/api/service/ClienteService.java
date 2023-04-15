package com.truper.salespoint.api.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.truper.salespoint.api.exception.NotFoundException;
import com.truper.salespoint.api.model.Cliente;
import com.truper.salespoint.api.repository.ClienteRepository;
import com.truper.salespoint.api.util.ServicesUtil;

import jakarta.transaction.Transactional;

@Service
public class ClienteService {
	@Autowired
	ClienteRepository clienteRepository;
	
	public ArrayList<Cliente> getClientes(){
		return (ArrayList<Cliente>) clienteRepository.findAllClean();
	}

	public Cliente getCliente(Long id){
		Cliente clienteToSearch = clienteRepository.findByIdExistance(id);
		if(clienteToSearch == null)
			throw new NotFoundException(new Cliente(), id);
		else
			return clienteToSearch;
		
	}

	public Cliente loadCliente(Cliente cliente) {
		return clienteRepository.save(cliente);
	}
	
	@Transactional
	public int deleteCliente(Long id) {
		return clienteRepository.logicDelete(id);
	}
	
	public Cliente getValuedElement(  Cliente objectToEval) {

		if(objectToEval.getId() != null) {
			Cliente clienteToUpdate = this.getCliente(objectToEval.getId());
			if(clienteToUpdate != null) {
				objectToEval = new ServicesUtil<Cliente>(objectToEval).getObjectToUpdate(clienteToUpdate);
				objectToEval.setFechaRegistro(clienteToUpdate.getFechaRegistro());
			}
		}
		return objectToEval;
	}
}
