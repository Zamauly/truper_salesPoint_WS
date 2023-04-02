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

import com.truper.salespoint.api.exception.ListaCompraNotFoundException;
import com.truper.salespoint.api.exception.ProductoNotFoundException;
import com.truper.salespoint.api.model.ListaCompra;
import com.truper.salespoint.api.model.ListaDetalle;
import com.truper.salespoint.api.model.Producto;
import com.truper.salespoint.api.service.ListaCompraService;
import com.truper.salespoint.api.service.ListaDetalleService;
import com.truper.salespoint.api.service.ProductoService;

@RestController
@RequestMapping("/lista-detalle")
public class ListaDetalleController {
	@Autowired
	ListaDetalleService listaDetalleService;
	@Autowired
	ProductoService productoService;
	@Autowired
	ListaCompraService listaCompraService;

	private static final Logger _log = LoggerFactory.getLogger(ListaDetalleController.class);
	
	@GetMapping()
	public ArrayList<ListaDetalle> getListasDetalle(){
		return this.listaDetalleService.getListasDetalle();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ListaDetalle> getListaDetalle(@PathVariable Long id) {
		return ResponseEntity.ok(listaDetalleService.getListaDetalle(id));		
	}
	
	@PostMapping()
	public ListaDetalle loadListaDetalle(@RequestBody ListaDetalle listaDetalle){

		_log.info(" Producto id por verificar"+listaDetalle.getProducto().getId());
		Producto valuedProducto = productoService.getProducto(listaDetalle.getProducto().getId());
		if(!valuedProducto.getActivo())
			throw new ProductoNotFoundException(listaDetalle.getProducto().getId());

		_log.info(" Lista Compra id por verificar"+listaDetalle.getListaCompra().getId());
		ListaCompra valuedListaCompra = listaCompraService.getListaCompra(listaDetalle.getListaCompra().getId());
		if(!valuedListaCompra.getActivo())
			throw new ListaCompraNotFoundException(listaDetalle.getListaCompra().getId());
		else
			return this.listaDetalleService.loadListaDetalle(listaDetalle);
		
	}
}
