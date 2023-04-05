package com.truper.salespoint.api.controller;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.truper.salespoint.api.commons.Constants;
import com.truper.salespoint.api.exception.ListaCompraNotFoundException;
import com.truper.salespoint.api.exception.ProductoNotFoundException;
import com.truper.salespoint.api.exception.ResponseException;
import com.truper.salespoint.api.model.ListaDetalle;
import com.truper.salespoint.api.service.ListaDetalleService;
import com.truper.salespoint.api.service.RequestModel;
import com.truper.salespoint.api.service.ResponseModel;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/lista-detalle")
public class ListaDetalleController {
	@Autowired
	ListaDetalleService listaDetalleService;

	private static final Logger _log = LoggerFactory.getLogger(ListaDetalleController.class);
	
	@GetMapping()
	public ResponseEntity<ResponseModel<?>> getListasDetalle(){
		return ResponseEntity.ok(new ResponseModel<ArrayList<ListaDetalle>>("OK","Se ha listado correctamente", this.listaDetalleService.getListasDetalle()));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ResponseModel<?>> getListaDetalle(@PathVariable Long id) {
		return ResponseEntity.ok(new ResponseModel<ListaDetalle>("OK","Se ha encontrado el registro", listaDetalleService.getListaDetalle(id)));
	}
	
	@PostMapping()
	public ResponseEntity<ResponseModel<?>> loadListaDetalle(@Valid @RequestBody RequestModel<ListaDetalle> listaDetalleRequest){
		try {
			ListaDetalle listaDetalleToSave = listaDetalleService.getValuedElement(listaDetalleRequest.getData());
			boolean valueProduct = listaDetalleService.verificateProduct(listaDetalleToSave);
			if(!valueProduct) 
				throw new ProductoNotFoundException(listaDetalleToSave.getProducto().getId());
			
			boolean valueListaCompra = listaDetalleService.verificateSellList(listaDetalleToSave);			
			if(!valueListaCompra) 
				throw new ListaCompraNotFoundException(listaDetalleToSave.getListaCompra().getId());
			ListaDetalle newVar = this.listaDetalleService.loadListaDetalle(listaDetalleToSave);
			return ResponseEntity.ok(new ResponseModel<ListaDetalle>("OK","Se ha cargado Correctamente",newVar));
						
		}catch(ListaCompraNotFoundException | ProductoNotFoundException err) {
			_log.error(" Error at trying to load ListaDetalle: "+err.getMessage());
			ResponseException responseExp = new ResponseException(Constants.validateException(err.getClass().getName()),err.getMessage());
			return ResponseEntity.status(404).body(new ResponseModel<ResponseException>("ERROR"," Error al cargar Lista detalle ",responseExp));
		} 
				
	}
}
