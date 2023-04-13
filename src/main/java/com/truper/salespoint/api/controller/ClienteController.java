package com.truper.salespoint.api.controller;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/*import org.slf4j.Logger;
import org.slf4j.LoggerFactory;*/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.truper.salespoint.api.commons.Constants;
import com.truper.salespoint.api.exception.ClienteNotFoundException;
import com.truper.salespoint.api.exception.ResponseException;
import com.truper.salespoint.api.model.Cliente;
import com.truper.salespoint.api.service.ClienteService;
import com.truper.salespoint.api.service.RequestModel;
import com.truper.salespoint.api.service.ResponseModel;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {
	@Autowired
	ClienteService clienteService;
	
	private static final Logger _log = LoggerFactory.getLogger(ClienteController.class);
	
	@GetMapping(produces = "application/json")
	public ResponseEntity<ResponseModel<?>> getClientes(){
		return ResponseEntity.ok(new ResponseModel<ArrayList<Cliente>>("OK","Se ha listado correctamente",this.clienteService.getClientes()));
	}
	
	@GetMapping(path="/{id}", produces = "application/json")
	public ResponseEntity<ResponseModel<?>> getCliente(@PathVariable Long id) {
		return ResponseEntity.ok(new ResponseModel<Cliente>("OK","Se ha listado correctamente",clienteService.getCliente(id)));		
	}
	
	@PostMapping(produces = "application/json")
	public ResponseEntity<ResponseModel<?>> loadCliente(@Valid @RequestBody RequestModel<Cliente> clienteRequest,  BindingResult bindingResult){
		try {
			
			Cliente clienteToSave = clienteService.getValuedElement(clienteRequest.getData());
			Cliente toSaveCliente = this.clienteService.loadCliente(clienteToSave);
			return ResponseEntity.ok(new ResponseModel<Cliente>("OK","Se ha cargado Correctamente",toSaveCliente));
			
		}catch(ClienteNotFoundException err) {
			_log.error(" Error at trying to load Cliente: "+err.getMessage());
			ResponseException responseExp = new ResponseException(Constants.validateException(err.getClass().getName()),err.getMessage());
			return ResponseEntity.status(404).body(new ResponseModel<ResponseException>("ERROR"," Error al cargar Cliente ",responseExp));
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ResponseModel<?>> updateCliente(@PathVariable(value = "id") Long id, @Valid @RequestBody RequestModel<Cliente> clienteRequest){
		try {
			clienteRequest.getData().setId(id);
			Cliente productoToSave = clienteService.getValuedElement(clienteRequest.getData());
			final  Cliente toSaveProducto = this.clienteService.loadCliente(productoToSave);
			return ResponseEntity.ok(new ResponseModel<Cliente>("OK","Se ha cargado Correctamente",toSaveProducto));
			
		}catch(ClienteNotFoundException err) {
			_log.error(" Error at trying to update Cliente: "+err.getMessage());
			ResponseException responseExp = new ResponseException(Constants.validateException(err.getClass().getName()),err.getMessage());
			return ResponseEntity.status(404).body(new ResponseModel<ResponseException>("ERROR"," Error al actualizar Producto ",responseExp));
		}
		
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseModel<?>> deleteCliente(@PathVariable(value = "id") Long id ){
		try {
			int varToCheck = clienteService.deleteCliente(id);
			_log.info(" Result at trying to delete : "+varToCheck);
			if(varToCheck > 0)
				return ResponseEntity.ok(new ResponseModel<Object>("OK","Se ha eliminado Correctamente",null));
			else
				return ResponseEntity.internalServerError().body(new ResponseModel<Object>("Error","No Se ha eliminado Correctamente",null));
		}catch(ClienteNotFoundException err) {
			_log.error(" Error at trying to Detele Producto: "+err.getMessage());
			ResponseException responseExp = new ResponseException(Constants.validateException(err.getClass().getName()),err.getMessage());
			return ResponseEntity.status(404).body(new ResponseModel<ResponseException>("ERROR"," Error al eliminar Cliente ",responseExp));
		}
	}
}
