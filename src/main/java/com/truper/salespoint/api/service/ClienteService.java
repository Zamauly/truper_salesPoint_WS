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
		return (ArrayList<Cliente>) clienteRepository.findAll();
	}
	
	public Cliente getCliente(Long id){
		return clienteRepository.findById(id).orElseThrow(() -> new ClienteNotFoundException(id));
	}
	
	public Cliente loadCliente(Cliente cliente) {
		return clienteRepository.save(cliente);
	}
	
}
