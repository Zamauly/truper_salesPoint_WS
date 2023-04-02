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

import com.truper.salespoint.api.exception.ClienteNotFoundException;
import com.truper.salespoint.api.model.Cliente;
import com.truper.salespoint.api.model.ListaCompra;
import com.truper.salespoint.api.service.ClienteService;
import com.truper.salespoint.api.service.ListaCompraService;

@RestController
@RequestMapping("/lista-compra")
public class ListaCompraController {
	@Autowired
	ListaCompraService listaCompraService;
	@Autowired
	ClienteService clienteService;
	
	private static final Logger _log = LoggerFactory.getLogger(ListaCompraController.class);
	
	@GetMapping()
	public ArrayList<ListaCompra> getListasCompra(){
		return this.listaCompraService.getListasCompras();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ListaCompra> getListaCompra(@PathVariable Long id) {
		return ResponseEntity.ok(listaCompraService.getListaCompra(id));		
	}
	
	@PostMapping()
	public ListaCompra loadListaCompra(@RequestBody ListaCompra listaCompra){
		_log.info(" Cliente id por verificar"+listaCompra.getCliente().getId());
		Cliente valuedCliente = clienteService.getCliente(listaCompra.getCliente().getId());
		if(valuedCliente.getActivo())
			return this.listaCompraService.loadListaCompra(listaCompra);
		else 
			throw new ClienteNotFoundException(listaCompra.getCliente().getId());
	}
}
