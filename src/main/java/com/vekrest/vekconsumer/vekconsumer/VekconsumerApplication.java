package com.vekrest.vekconsumer.vekconsumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VekconsumerApplication implements CommandLineRunner {
	private static final Logger LOG = LoggerFactory.getLogger(VekconsumerApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(VekconsumerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		LOG.info("VEKREST -> VEKCONSUMER - INICIALIZADO COM SUCESSO!");
	}
}