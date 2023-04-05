package com.truper.salespoint.api.controller;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.truper.salespoint.api.commons.Constants;
import com.truper.salespoint.api.exception.ClienteNotFoundException;
import com.truper.salespoint.api.exception.ResponseException;
import com.truper.salespoint.api.model.Cliente;
import com.truper.salespoint.api.service.ClienteService;
import com.truper.salespoint.api.service.ResponseModel;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
	@Autowired
	ClienteService clienteService;
	
	private static final Logger _log = LoggerFactory.getLogger(ClienteController.class);
	
	@GetMapping()
	public ResponseEntity<ResponseModel<?>> getClientes(){
		return ResponseEntity.ok(new ResponseModel<ArrayList<Cliente>>("OK","Se ha listado correctamente",this.clienteService.getClientes()));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ResponseModel<?>> getCliente(@PathVariable Long id) {
		return ResponseEntity.ok(new ResponseModel<Cliente>("OK","Se ha listado correctamente",clienteService.getCliente(id)));		
	}
	
	@PostMapping()
	public ResponseEntity<ResponseModel<?>> loadCliente(@RequestBody Cliente cliente){
		try {

			cliente = clienteService.getValuedElement(cliente);
			Cliente toSaveCliente = this.clienteService.loadCliente(cliente);
			return ResponseEntity.ok(new ResponseModel<Cliente>("OK","Se ha cargado Correctamente",toSaveCliente));
			
		}catch(ClienteNotFoundException err) {
			_log.error(" Error at trying to load Cliente: "+err.getMessage());
			ResponseException responseExp = new ResponseException(Constants.validateException(err.getClass().getName()),err.getMessage());
			return ResponseEntity.status(404).body(new ResponseModel<ResponseException>("ERROR"," Error al cargar Cliente ",responseExp));
		}
	}
}
