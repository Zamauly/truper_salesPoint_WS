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
import com.truper.salespoint.api.exception.ProductoNotFoundException;
import com.truper.salespoint.api.exception.ResponseException;
import com.truper.salespoint.api.model.Producto;
import com.truper.salespoint.api.service.ProductoService;
import com.truper.salespoint.api.service.RequestModel;
import com.truper.salespoint.api.service.ResponseModel;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/producto")
public class ProductoController {
	@Autowired
	ProductoService productoService;
	
	private static final Logger _log = LoggerFactory.getLogger(ProductoController.class);
	
	@GetMapping()
	public ResponseEntity<ResponseModel<?>> getProductos(){
		return ResponseEntity.ok(new ResponseModel<ArrayList<Producto>>("OK","Se ha listado correctamente",this.productoService.getProductos()));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ResponseModel<?>> getProducto(@PathVariable Long id) {
		return ResponseEntity.ok(new ResponseModel<Producto>("OK","Se ha listado correctamente",productoService.getProducto(id)));		
	}
	
	@PostMapping()
	public ResponseEntity<ResponseModel<?>> loadProducto(@Valid @RequestBody RequestModel<Producto> productoRequest){
		try {

			Producto productoToSave = productoService.getValuedElement(productoRequest.getData());
			final Producto toSaveProducto = this.productoService.loadProducto(productoToSave);
			return ResponseEntity.ok(new ResponseModel<Producto>("OK","Se ha cargado Correctamente",toSaveProducto));
			
		}catch(ProductoNotFoundException err) {
			_log.error(" Error at trying to load Prodcuto: "+err.getMessage());
			ResponseException responseExp = new ResponseException(Constants.validateException(err.getClass().getName()),err.getMessage());
			return ResponseEntity.status(404).body(new ResponseModel<ResponseException>("ERROR"," Error al cargar Producto ",responseExp));
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ResponseModel<?>> updateProducto(@PathVariable(value = "id") Long id, @Valid @RequestBody RequestModel<Producto> productoRequest){
		try {
			productoRequest.getData().setId(id);
			Producto productoToSave = productoService.getValuedElement(productoRequest.getData());
			final  Producto toSaveProducto = this.productoService.loadProducto(productoToSave);
			return ResponseEntity.ok(new ResponseModel<Producto>("OK","Se ha cargado Correctamente",toSaveProducto));
			
		}catch(ProductoNotFoundException err) {
			_log.error(" Error at trying to update Cliente: "+err.getMessage());
			ResponseException responseExp = new ResponseException(Constants.validateException(err.getClass().getName()),err.getMessage());
			return ResponseEntity.status(404).body(new ResponseModel<ResponseException>("ERROR"," Error al actualizar Producto ",responseExp));
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseModel<?>> deleteProducto(@PathVariable(value = "id") Long id ){
		try {
			int varToCheck = productoService.deleteProducto(id);
			_log.info(" Result at trying to delete : "+varToCheck);
			if(varToCheck > 0)
				return ResponseEntity.ok(new ResponseModel<Object>("OK","Se ha eliminado Correctamente",null));
			else
				return ResponseEntity.internalServerError().body(new ResponseModel<Object>("Error","No Se ha eliminado Correctamente",null));
		}catch(ProductoNotFoundException err) {
			_log.error(" Error at trying to Detele Producto: "+err.getMessage());
			ResponseException responseExp = new ResponseException(Constants.validateException(err.getClass().getName()),err.getMessage());
			return ResponseEntity.status(404).body(new ResponseModel<ResponseException>("ERROR"," Error al eliminar Producto ",responseExp));
		}
	}
}
