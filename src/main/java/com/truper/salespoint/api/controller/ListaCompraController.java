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
import com.truper.salespoint.api.exception.ClienteNotFoundException;
import com.truper.salespoint.api.exception.ResponseException;
import com.truper.salespoint.api.model.ListaCompra;
import com.truper.salespoint.api.service.ListaCompraService;
import com.truper.salespoint.api.service.RequestModel;
import com.truper.salespoint.api.service.ResponseModel;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/lista-compra")
public class ListaCompraController {
	@Autowired
	ListaCompraService listaCompraService;
	
	private static final Logger _log = LoggerFactory.getLogger(ListaCompraController.class);
	
	@GetMapping()
	public ResponseEntity<ResponseModel<?>> getListasCompra(){
		return ResponseEntity.ok(new ResponseModel<ArrayList<ListaCompra>>("OK","Se ha listado correctamente",this.listaCompraService.getListasCompras()));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ResponseModel<?>> getListaCompra(@PathVariable Long id) {
		return ResponseEntity.ok(new ResponseModel<ListaCompra>("OK","Se ha listado correctamente",listaCompraService.getListaCompra(id)));		
	}
	
	@PostMapping()
	public ResponseEntity<ResponseModel<?>> loadListaCompra(@Valid @RequestBody RequestModel<ListaCompra> listaCompraRequest){
		try {
			ListaCompra listaCompraToSave = listaCompraService.getValuedElement(listaCompraRequest.getData());
			boolean valueClient = listaCompraService.verificateClient(listaCompraToSave);
			if(!valueClient) 
				throw new ClienteNotFoundException(listaCompraToSave.getCliente().getId());
			ListaCompra toSaveListaCompra = listaCompraService.loadListaCompra(listaCompraToSave);
			return ResponseEntity.ok(new ResponseModel<ListaCompra>("OK","Se ha cargado Correctamente",toSaveListaCompra));
		}catch(ClienteNotFoundException err) {
			_log.error(" Error at trying to load ListaCompra: "+err.getMessage());
			ResponseException responseExp = new ResponseException(Constants.validateException(err.getClass().getName()),err.getMessage());
			return ResponseEntity.status(404).body(new ResponseModel<ResponseException>("ERROR"," Error al cargar Lista compra ",responseExp));
		}
	}
}
