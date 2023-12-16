package com.example.demo;

import lombok.extern.java.Log;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Log
@SpringBootApplication
public class DemoApplication {
//		implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

//	private final DataSource dataSource;
//
//	public DemoApplication(final DataSource dataSource) {
//		this.dataSource = dataSource;
//	}

//	@Override
//	public void run(final String... args) throws Exception {
//		log.info("DataSource: " + dataSource.toString());
//		final JdbcTemplate restTemplate = new JdbcTemplate(dataSource);
//		restTemplate.execute("select 1");
//	}
}
