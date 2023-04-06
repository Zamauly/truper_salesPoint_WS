package com.truper.salespoint.api.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.truper.salespoint.api.exception.ProductoNotFoundException;
import com.truper.salespoint.api.exception.InventaryException;
import com.truper.salespoint.api.model.Producto;
import com.truper.salespoint.api.repository.ProductoRepository;

import jakarta.transaction.Transactional;

@Service
public class ProductoService {
	@Autowired
	ProductoRepository productoRepository;

	public ArrayList<Producto> getProductos() {
		return (ArrayList<Producto>) productoRepository.findAllClean();
	}

	public Producto getProducto(Long id) {
		Producto productToSearch = productoRepository.findByIdExistance(id);
		if (productToSearch == null)
			throw new ProductoNotFoundException(id);
		else if (productToSearch.getExistencia() < 1)
			throw new InventaryException(productToSearch.getNombre(), productToSearch.getExistencia());
		else
			return productToSearch;
	}

	public Producto loadProducto(Producto producto) {
		return productoRepository.save(producto);
	}

	@Transactional
	public int deleteProducto(Long id) {
		return productoRepository.logicDelete(id);
	}

	public Producto getValuedElement(Producto objectToEval) {

		if (objectToEval.getId() != null) {
			Producto productoToUpdate = this.getProducto(objectToEval.getId());
			if (productoToUpdate != null) {
				objectToEval = new ServicesUtil<Producto>(objectToEval).getObjectToUpdate(productoToUpdate);
				objectToEval.setFechaRegistro(productoToUpdate.getFechaRegistro());
			}
		}
		return objectToEval;
	}
}
