package com.truper.salespoint.api.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.truper.salespoint.api.model.ListaDetalle;
import com.truper.salespoint.api.service.ListaDetalleService;

@RestController
@RequestMapping("/lista-detalle")
public class ListaDetalleController {
	@Autowired
	ListaDetalleService listaDetalleService;
	
	@GetMapping()
	public ArrayList<ListaDetalle> getClientes(){
		return this.listaDetalleService.getListasDetalle();
	}
	
	@PostMapping()
	public ListaDetalle loadCliente(@RequestBody ListaDetalle listaDetalle){
		return this.listaDetalleService.loadListaDetalle(listaDetalle);
	}
}
