package com.truper.salespoint.api.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.truper.salespoint.api.model.Producto;
import com.truper.salespoint.api.service.ProductoService;

@RestController
@RequestMapping("/producto")
public class ProductoController {
	@Autowired
	ProductoService productoService;
	
	@GetMapping()
	public ArrayList<Producto> getProductos(){
		return this.productoService.getProductos();
	}
	
	@PostMapping()
	public Producto loadProducto(@RequestBody Producto producto){
		return this.productoService.loadProducto(producto);
	}
}
