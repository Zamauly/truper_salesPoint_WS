package com.truper.salespoint.api.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.truper.salespoint.api.exception.NotFoundException;
import com.truper.salespoint.api.model.ListaCompra;
import com.truper.salespoint.api.model.ListaDetalle;
import com.truper.salespoint.api.model.Producto;
import com.truper.salespoint.api.repository.ListaDetalleRepository;
import com.truper.salespoint.api.util.ServicesUtil;

import jakarta.transaction.Transactional;

@Service
public class ListaDetalleService {
	@Autowired
	ListaDetalleRepository listaDetalleRepository;
	@Autowired
	ProductoService productoService;
	@Autowired
	ListaCompraService listaCompraService;
	//private static final Logger _log = LoggerFactory.getLogger(ListaDetalleService.class);
	
	public ArrayList<ListaDetalle> getListasDetalle(){
		return (ArrayList<ListaDetalle>) listaDetalleRepository.findAllClean();
	}

	public ListaDetalle getListaDetalle(Long id){
		ListaDetalle listaDetalleToSearch = listaDetalleRepository.findByIdAndActive(id);
		if(listaDetalleToSearch == null)
			throw new NotFoundException(new ListaDetalle(), 	id);
		else
			return listaDetalleToSearch;
		
	}
	
	public ListaDetalle loadListaDetalle(ListaDetalle listaDetalle) {
		
		return listaDetalleRepository.save(listaDetalle);
	}
	
	@Transactional
	public int deleteListaDetalle(Long id) {
		return listaDetalleRepository.logicDelete(id);
	}
	
	public ListaDetalle getValuedElement(ListaDetalle objectToEval) {

		if(objectToEval.getId() != null) {
			ListaDetalle listaDetalleToUpdate = this.getListaDetalle(objectToEval.getId());
			if(listaDetalleToUpdate != null) {
				objectToEval = new ServicesUtil<ListaDetalle>(objectToEval).getObjectToUpdate(listaDetalleToUpdate);
				objectToEval.setFechaRegistro(listaDetalleToUpdate.getFechaRegistro());
			}
		}
		return objectToEval;
	}
	
	public boolean verificateProduct(ListaDetalle listaDetalle) {
		if(listaDetalle.getProducto() != null ) {
			Producto valuedProducto = productoService.getProducto(listaDetalle.getProducto().getId());
			if(valuedProducto == null) 
				return false;
			
			if(valuedProducto.inventaryOperation(listaDetalle.getCantidad()))
				productoService.loadProducto(valuedProducto);
			
		}
		return true;
	}
	
	public boolean verificateSellList(ListaDetalle listaDetalle) {
		if(listaDetalle.getListaCompra() != null) {
			ListaCompra valuedListaCompra = listaCompraService.getListaCompra(listaDetalle.getListaCompra().getId());
			if(valuedListaCompra == null ) 
				return false;			
			
		}
		return true;
	}
}
