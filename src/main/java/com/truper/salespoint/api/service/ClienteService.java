package com.truper.salespoint.api.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.truper.salespoint.api.exception.ClienteNotFoundException;
import com.truper.salespoint.api.model.Cliente;
import com.truper.salespoint.api.repository.ClienteRepository;

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
			throw new ClienteNotFoundException(id);
		else
			return clienteToSearch;
		
	}

	public Cliente loadCliente(Cliente cliente) {
		return clienteRepository.save(cliente);
	}
	
	public Cliente getValuedElement(Cliente objectToEval) {

		if(objectToEval.getId() != null) {
			Cliente clienteToUpdate = this.getCliente(objectToEval.getId());
			if(clienteToUpdate != null)
				objectToEval = new ServicesUtil<Cliente>(objectToEval).getObjectToUpdate(clienteToUpdate);

		}
		return objectToEval;
	}
}
