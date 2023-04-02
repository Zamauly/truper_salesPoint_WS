package com.truper.salespoint.api.controller;

import java.util.ArrayList;

/*import org.slf4j.Logger;
import org.slf4j.LoggerFactory;*/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	//private static final Logger _log = LoggerFactory.getLogger(ClienteController.class);
	
	@GetMapping()
	public ArrayList<Cliente> getClientes(){
		return this.clienteService.getClientes();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> getCliente(@PathVariable Long id) {
		return ResponseEntity.ok(clienteService.getCliente(id));		
	}
	
	@PostMapping()
	public Cliente loadCliente(@RequestBody Cliente cliente){
		return this.clienteService.loadCliente(cliente);
	}
}
