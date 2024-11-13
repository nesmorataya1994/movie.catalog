package com.movie.catalog;

import org.springframework.boot.SpringApplication;

public class TestMovieCatalogApplication {

	public static void main(String[] args) {
		SpringApplication.from(MovieCatalogApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
