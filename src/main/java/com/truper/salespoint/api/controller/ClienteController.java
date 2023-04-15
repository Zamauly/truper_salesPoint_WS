package com.truper.salespoint.api.controller;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.truper.salespoint.api.exception.NotFoundException;
import com.truper.salespoint.api.exception.ResponseException;
import com.truper.salespoint.api.model.Cliente;
import com.truper.salespoint.api.payload.request.RequestModel;
import com.truper.salespoint.api.payload.response.ResponseModel;
import com.truper.salespoint.api.service.ClienteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {
	@Autowired
	ClienteService clienteService;
	
	private static final Logger _log = LoggerFactory.getLogger(ClienteController.class);
	
	@GetMapping(produces = "application/json")
	public ResponseEntity<ResponseModel<?>> getClientes(){
		return ResponseEntity.ok(new ResponseModel<ArrayList<Cliente>>(Constants._OK,Constants.BUSINESS_MSG.get("FOUND"), this.clienteService.getClientes()));
	}
	
	@GetMapping(path="/{id}", produces = "application/json")
	public ResponseEntity<ResponseModel<?>> getCliente(@PathVariable Long id) {
		return ResponseEntity.ok(new ResponseModel<Cliente>(Constants._OK,Constants.BUSINESS_MSG.get("FOUND"), clienteService.getCliente(id)));		
	}
	
	@PostMapping(produces = "application/json")
	public ResponseEntity<ResponseModel<?>> loadCliente(@Valid @RequestBody RequestModel<Cliente> clienteRequest,  BindingResult bindingResult){
		try {
			
			Cliente clienteToSave = clienteService.getValuedElement(clienteRequest.getData());
			Cliente toSaveCliente = this.clienteService.loadCliente(clienteToSave);
			return ResponseEntity.ok(new ResponseModel<Cliente>(Constants._OK,Constants.BUSINESS_MSG.get("LOAD"),toSaveCliente));
			
		}catch(NotFoundException err) {
			_log.error(" Error at trying to load Cliente: "+err.getMessage());
			ResponseException responseExp = new ResponseException(Constants.validateException(err.getClass().getName()),err.getMessage());
			return ResponseEntity.status(404).body(new ResponseModel<ResponseException>(Constants._ERROR,Constants.BUSINESS_EXCEPTIONS_MSG.get("NOT_ADD"),responseExp));
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ResponseModel<?>> updateCliente(@PathVariable(value = "id") Long id, @Valid @RequestBody RequestModel<Cliente> clienteRequest){
		try {
			clienteRequest.getData().setId(id);
			Cliente clienteToSave = clienteService.getValuedElement(clienteRequest.getData());
			final  Cliente toSaveCliente = this.clienteService.loadCliente(clienteToSave);
			return ResponseEntity.ok(new ResponseModel<Cliente>(Constants._OK,Constants.BUSINESS_MSG.get("LOAD"),toSaveCliente));
			
		}catch(NotFoundException err) {
			_log.error(" Error at trying to update Cliente: "+err.getMessage());
			ResponseException responseExp = new ResponseException(Constants.validateException(err.getClass().getName()),err.getMessage());
			return ResponseEntity.status(404).body(new ResponseModel<ResponseException>(Constants._ERROR,Constants.BUSINESS_EXCEPTIONS_MSG.get("NOT_UPDATE"), responseExp));
		}
		
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseModel<?>> deleteCliente(@PathVariable(value = "id") Long id ){
		try {
			int varToCheck = clienteService.deleteCliente(id);
			_log.info(" Result at trying to delete : "+varToCheck);
			if(varToCheck > 0)
				return ResponseEntity.ok(new ResponseModel<Object>(Constants._OK,Constants.BUSINESS_MSG.get("DELETE"),null));
			else
				return ResponseEntity.internalServerError().body(new ResponseModel<Object>(Constants._ERROR,Constants.BUSINESS_EXCEPTIONS_MSG.get("NOT_DELETE_PROCESS"),null));
			
		}catch(NotFoundException err) {
			_log.error(" Error at trying to Detele Cliente: "+err.getMessage());
			ResponseException responseExp = new ResponseException(Constants.validateException(err.getClass().getName()),err.getMessage());
			return ResponseEntity.status(404).body(new ResponseModel<ResponseException>(Constants._ERROR,Constants.BUSINESS_EXCEPTIONS_MSG.get("NOT_DELETE_EXCEPTION"),responseExp));
		}
	}
}
