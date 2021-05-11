package de3.vanelle_fiorini.projet_big_data;

import de3.vanelle_fiorini.projet_big_data.service.impl.HiveClientServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProjetBigDataApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetBigDataApplication.class, args);
	}

}
