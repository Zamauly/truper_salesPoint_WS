package com.truper.salespoint.api.controller;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import com.truper.salespoint.api.model.ListaCompra;
import com.truper.salespoint.api.payload.request.RequestModel;
import com.truper.salespoint.api.payload.response.ResponseModel;
import com.truper.salespoint.api.service.ListaCompraService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/lista-compra")
public class ListaCompraController {
	@Autowired
	ListaCompraService listaCompraService;
	
	private static final Logger _log = LoggerFactory.getLogger(ListaCompraController.class);
	
	@GetMapping()
	public ResponseEntity<ResponseModel<?>> getListasCompra(){
		return ResponseEntity.ok(new ResponseModel<ArrayList<ListaCompra>>(Constants._OK,Constants.BUSINESS_MSG.get("FOUND"),this.listaCompraService.getListasCompras()));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ResponseModel<?>> getListaCompra(@PathVariable Long id) {
		return ResponseEntity.ok(new ResponseModel<ListaCompra>(Constants._OK,Constants.BUSINESS_MSG.get("FOUND"),listaCompraService.getListaCompra(id)));		
	}
	
	@PostMapping()
	public ResponseEntity<ResponseModel<?>> loadListaCompra(@Valid @RequestBody RequestModel<ListaCompra> listaCompraRequest){
		try {
			ListaCompra listaCompraToSave = listaCompraService.getValuedElement(listaCompraRequest.getData());
			boolean valueClient = listaCompraService.verificateClient(listaCompraToSave);
			if(!valueClient) 
				throw new NotFoundException(listaCompraToSave.getCliente(), listaCompraToSave.getCliente().getId());
			ListaCompra toSaveListaCompra = listaCompraService.loadListaCompra(listaCompraToSave);
			return ResponseEntity.ok(new ResponseModel<ListaCompra>(Constants._OK,Constants.BUSINESS_MSG.get("LOAD"),toSaveListaCompra));
		}catch(NotFoundException err) {
			_log.error(" Error at trying to load ListaCompra: "+err.getMessage());
			ResponseException responseExp = new ResponseException(Constants.validateException(err.getClass().getName()),err.getMessage());
			return ResponseEntity.status(404).body(new ResponseModel<ResponseException>(Constants._ERROR,Constants.BUSINESS_EXCEPTIONS_MSG.get("NOT_ADD"),responseExp));
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ResponseModel<?>> updateListacompra(@PathVariable(value = "id") Long id, @Valid @RequestBody RequestModel<ListaCompra> listaCompraRequest){
		try {
			listaCompraRequest.getData().setId(id);
			ListaCompra listaCompraToSave = listaCompraService.getValuedElement(listaCompraRequest.getData());
			boolean valueClient = listaCompraService.verificateClient(listaCompraToSave);
			if(!valueClient) 
				throw new NotFoundException(listaCompraToSave.getCliente(), listaCompraToSave.getCliente().getId());
			
			final  ListaCompra toSaveListaCompra = this.listaCompraService.loadListaCompra(listaCompraToSave);
			return ResponseEntity.ok(new ResponseModel<ListaCompra>(Constants._OK,Constants.BUSINESS_MSG.get("LOAD"),toSaveListaCompra));
			
		}catch(NotFoundException err) {
			_log.error(" Error at trying to update Lista Compra: "+err.getMessage());
			ResponseException responseExp = new ResponseException(Constants.validateException(err.getClass().getName()),err.getMessage());
			return ResponseEntity.status(404).body(new ResponseModel<ResponseException>(Constants._ERROR,Constants.BUSINESS_EXCEPTIONS_MSG.get("NOT_UPDATE"),responseExp));
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseModel<?>> deleteListaCompra(@PathVariable(value = "id") Long id ){
		try {
			int varToCheck = listaCompraService.deleteListaCompra(id);
			_log.info(" Result at trying to delete : "+varToCheck);
			if(varToCheck > 0)
				return ResponseEntity.ok(new ResponseModel<Object>(Constants._OK,Constants.BUSINESS_MSG.get("DELETE"),null));
			else
				return ResponseEntity.internalServerError().body(new ResponseModel<Object>(Constants._ERROR,Constants.BUSINESS_EXCEPTIONS_MSG.get("NOT_DELETE_PROCESS"),null));
		}catch(NotFoundException err) {
			_log.error(" Error at trying to Detele Lista compra: "+err.getMessage());
			ResponseException responseExp = new ResponseException(Constants.validateException(err.getClass().getName()),err.getMessage());
			return ResponseEntity.status(404).body(new ResponseModel<ResponseException>(Constants._ERROR,Constants.BUSINESS_EXCEPTIONS_MSG.get("NOT_DELETE_EXCEPTION"),responseExp));
		}
	}
}
