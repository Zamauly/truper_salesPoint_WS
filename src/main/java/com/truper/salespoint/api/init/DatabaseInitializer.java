package com.truper.salespoint.api.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.Date;

import com.truper.salespoint.api.model.Cliente;
import com.truper.salespoint.api.repository.ClienteRepository;

@Configuration
public class DatabaseInitializer {	
	  private static final Logger _log = LoggerFactory.getLogger(DatabaseInitializer.class);

	  @Bean
	  CommandLineRunner initDatabase(ClienteRepository repository) {
		Date initialDate = new Date();
	    return args -> {
	      _log.info("Preloading " + repository.save(new Cliente("Orion Dev", initialDate, initialDate, 1)));
	      _log.info("Preloading " + repository.save(new Cliente("Chuy Dev", initialDate, initialDate, 2)));
	    };
	  }
}
