package com.truper.salespoint.api.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.truper.salespoint.api.model.ListaCompra;
import com.truper.salespoint.api.service.ListaCompraService;

@RestController
@RequestMapping("/lista-compra")
public class ListaCompraController {
	@Autowired
	ListaCompraService listaCompraService;
	
	@GetMapping()
	public ArrayList<ListaCompra> getClientes(){
		return this.listaCompraService.getListasCompras();
	}
	
	@PostMapping()
	public ListaCompra loadCliente(@RequestBody ListaCompra listaCompra){
		return this.listaCompraService.loadListaCompra(listaCompra);
	}
}
