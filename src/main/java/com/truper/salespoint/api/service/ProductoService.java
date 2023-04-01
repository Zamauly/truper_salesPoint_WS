package com.truper.salespoint.api.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.truper.salespoint.api.model.Producto;
import com.truper.salespoint.api.repository.ProductoRepository;

@Service
public class ProductoService {
	@Autowired
	ProductoRepository productoRepository;
	
	public ArrayList<Producto> getProductos(){
		return (ArrayList<Producto>) productoRepository.findAll();
	}
	
	public Producto loadProducto(Producto producto) {
		return productoRepository.save(producto);
	}
	
}
