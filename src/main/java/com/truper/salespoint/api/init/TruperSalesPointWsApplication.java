package com.truper.salespoint.api.init;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages={"com.truper.salespoint.api"})
@EnableJpaRepositories("com.truper.salespoint.api.repository")
@EntityScan("com.truper.salespoint.api.model")
@ComponentScan("com.truper.salespoint.api")
public class TruperSalesPointWsApplication  extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(TruperSalesPointWsApplication.class, args);
	}

}
