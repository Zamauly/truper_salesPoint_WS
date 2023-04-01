package com.truper.salespoint.api.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.Date;

import com.truper.salespoint.api.model.Cliente;
import com.truper.salespoint.api.model.ListaCompra;
import com.truper.salespoint.api.model.ListaDetalle;
import com.truper.salespoint.api.model.Producto;
import com.truper.salespoint.api.repository.ClienteRepository;
import com.truper.salespoint.api.repository.ListaCompraRepository;
import com.truper.salespoint.api.repository.ListaDetalleRepository;
import com.truper.salespoint.api.repository.ProductoRepository;

@Configuration
public class DatabaseInitializer {	
	  private static final Logger _log = LoggerFactory.getLogger(DatabaseInitializer.class);

	  @Bean
	  CommandLineRunner initDatabase(ClienteRepository clienteRepository, ProductoRepository productoRepository, 
			  				ListaCompraRepository listaCompraRepository, ListaDetalleRepository listaDetalleRepository) {
		Date initialDate = new Date();
		Cliente initClient1 = new Cliente("Orion Dev", initialDate, initialDate, 1, true);
		Cliente initClient2 = new Cliente("Chuy Dev", initialDate, initialDate, 2, true);
		Producto initProducto1 = new Producto("TRP-0AF37", "Tijeras grandes para jardineria", "Tijeras Jardin", initialDate, initialDate, 25, true);
		Producto initProducto2 =new Producto("TRP-02F3G", "Juego de Dados para matraca de 00pg hasta 12pg", "Dados Dif Tam", initialDate, initialDate, 12, true);
		ListaCompra initListaCompra1 = new ListaCompra(initClient1, "Lista Semanal", "Entregar directamente al cliente", initialDate, initialDate, true);
		ListaCompra initListaCompra2 = new ListaCompra(initClient1, "Lista Mensual", "Entregar directamente al cliente", initialDate, initialDate, true);
		ListaCompra initListaCompra3= new ListaCompra(initClient2, "Lista Semanal", "Entregar a encargado en tienda \"Cosme Fulanito\"", initialDate, initialDate, true);
				
		return args -> {
	      _log.info("Preloading " + clienteRepository.save(initClient1));
	      _log.info("Preloading " + clienteRepository.save(initClient2));
	      _log.info("Preloading " + productoRepository.save(initProducto1));
	      _log.info("Preloading " + productoRepository.save(initProducto2));
	      _log.info("Preloading " + listaCompraRepository.save(initListaCompra1));
	      _log.info("Preloading " + listaCompraRepository.save(initListaCompra2));
	      _log.info("Preloading " + listaCompraRepository.save(initListaCompra3));
	      _log.info("Preloading " + listaDetalleRepository.save(new ListaDetalle(initListaCompra1, initProducto1, 2, initialDate, initialDate, true)));
	      _log.info("Preloading " + listaDetalleRepository.save(new ListaDetalle(initListaCompra1, initProducto2, 1, initialDate, initialDate, true)));
	      _log.info("Preloading " + listaDetalleRepository.save(new ListaDetalle(initListaCompra2, initProducto1, 5, initialDate, initialDate, true)));
	      _log.info("Preloading " + listaDetalleRepository.save(new ListaDetalle(initListaCompra2, initProducto2, 2, initialDate, initialDate, true)));
	      _log.info("Preloading " + listaDetalleRepository.save(new ListaDetalle(initListaCompra3, initProducto1, 3, initialDate, initialDate, true)));
	      _log.info("Preloading " + listaDetalleRepository.save(new ListaDetalle(initListaCompra3, initProducto2, 1, initialDate, initialDate, true)));
	    };
	  }
}
