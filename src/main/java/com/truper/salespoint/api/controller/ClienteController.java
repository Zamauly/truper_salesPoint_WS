package com.truper.salespoint.api.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.truper.salespoint.api.model.Cliente;
import com.truper.salespoint.api.service.ClienteService;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
	@Autowired
	ClienteService clienteService;
	
	@GetMapping()
	public ArrayList<Cliente> getClientes(){
		return this.clienteService.getClientes();
	}
	
	@PostMapping()
	public Cliente loadCliente(@RequestBody Cliente cliente){
		return this.clienteService.loadCliente(cliente);
	}
}
