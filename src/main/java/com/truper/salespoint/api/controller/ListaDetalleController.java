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
import com.truper.salespoint.api.model.ListaDetalle;
import com.truper.salespoint.api.payload.request.RequestModel;
import com.truper.salespoint.api.payload.response.ResponseModel;
import com.truper.salespoint.api.service.ListaDetalleService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/lista-detalle")
public class ListaDetalleController {
	@Autowired
	ListaDetalleService listaDetalleService;

	private static final Logger _log = LoggerFactory.getLogger(ListaDetalleController.class);
	
	@GetMapping()
	public ResponseEntity<ResponseModel<?>> getListasDetalle(){
		return ResponseEntity.ok(new ResponseModel<ArrayList<ListaDetalle>>(Constants._OK,Constants.BUSINESS_MSG.get("FOUND"), this.listaDetalleService.getListasDetalle()));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ResponseModel<?>> getListaDetalle(@PathVariable Long id) {
		return ResponseEntity.ok(new ResponseModel<ListaDetalle>(Constants._OK,Constants.BUSINESS_MSG.get("FOUND"), listaDetalleService.getListaDetalle(id)));
	}
	
	@PostMapping()
	public ResponseEntity<ResponseModel<?>> loadListaDetalle(@Valid @RequestBody RequestModel<ListaDetalle> listaDetalleRequest){
		try {
			ListaDetalle listaDetalleToSave = listaDetalleService.getValuedElement(listaDetalleRequest.getData());
			boolean valueProduct = listaDetalleService.verificateProduct(listaDetalleToSave);
			if(!valueProduct) 
				throw new NotFoundException(listaDetalleToSave.getProducto(),listaDetalleToSave.getProducto().getId());
			
			boolean valueListaCompra = listaDetalleService.verificateSellList(listaDetalleToSave);			
			if(!valueListaCompra) 
				throw new NotFoundException(listaDetalleToSave.getListaCompra(),listaDetalleToSave.getListaCompra().getId());
			ListaDetalle toSaveListaDetalle = this.listaDetalleService.loadListaDetalle(listaDetalleToSave);
			return ResponseEntity.ok(new ResponseModel<ListaDetalle>(Constants._OK,Constants.BUSINESS_MSG.get("LOAD"),toSaveListaDetalle));
						
		}catch( NotFoundException err) {
			_log.error(" Error at trying to load ListaDetalle: "+err.getMessage());
			ResponseException responseExp = new ResponseException(Constants.validateException(err.getClass().getName()),err.getMessage());
			return ResponseEntity.status(404).body(new ResponseModel<ResponseException>(Constants._ERROR,Constants.BUSINESS_EXCEPTIONS_MSG.get("NOT_ADD"),responseExp));
		} 
				
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ResponseModel<?>> updateListaDetalle(@PathVariable(value = "id") Long id, @Valid @RequestBody RequestModel<ListaDetalle> listaDetalleRequest){
		try {
			listaDetalleRequest.getData().setId(id);
			ListaDetalle listaDetalleToSave = listaDetalleService.getValuedElement(listaDetalleRequest.getData());
			boolean valueProduct = listaDetalleService.verificateProduct(listaDetalleToSave);
			if(!valueProduct) 
				throw new NotFoundException(listaDetalleToSave.getProducto(),listaDetalleToSave.getProducto().getId());
			
			boolean valueListaCompra = listaDetalleService.verificateSellList(listaDetalleToSave);			
			if(!valueListaCompra) 
				throw new NotFoundException(listaDetalleToSave.getListaCompra(),listaDetalleToSave.getListaCompra().getId());
			
			ListaDetalle toSaveListaDetalle = this.listaDetalleService.loadListaDetalle(listaDetalleToSave);
			return ResponseEntity.ok(new ResponseModel<ListaDetalle>(Constants._OK,Constants.BUSINESS_MSG.get("LOAD"),toSaveListaDetalle));
			
		}catch( NotFoundException err) {
			_log.error(" Error at trying to update Lista Detalle: "+err.getMessage());
			ResponseException responseExp = new ResponseException(Constants.validateException(err.getClass().getName()),err.getMessage());
			return ResponseEntity.status(404).body(new ResponseModel<ResponseException>(Constants._ERROR,Constants.BUSINESS_EXCEPTIONS_MSG.get("NOT_UPDATE"),responseExp));
		}
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseModel<?>> deleteListaDetalle(@PathVariable(value = "id") Long id ){
		try {
			int varToCheck = listaDetalleService.deleteListaDetalle(id);
			_log.info(" Result at trying to delete : "+varToCheck);
			if(varToCheck > 0)
				return ResponseEntity.ok(new ResponseModel<Object>(Constants._OK,Constants.BUSINESS_MSG.get("DELETE"),null));
			else
				return ResponseEntity.internalServerError().body(new ResponseModel<Object>(Constants._ERROR,Constants.BUSINESS_EXCEPTIONS_MSG.get("NOT_DELETE_PROCESS"),null));
		}catch(NotFoundException err) {
			_log.error(" Error at trying to Detele Lista Detalle: "+err.getMessage());
			ResponseException responseExp = new ResponseException(Constants.validateException(err.getClass().getName()),err.getMessage());
			return ResponseEntity.status(404).body(new ResponseModel<ResponseException>(Constants._ERROR,Constants.BUSINESS_EXCEPTIONS_MSG.get("NOT_DELETE_EXCEPTION"),responseExp));
		}
	}	
}
