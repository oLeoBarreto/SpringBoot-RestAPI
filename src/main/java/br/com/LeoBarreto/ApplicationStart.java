package br.com.LeoBarreto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ApplicationStart {

	public static void main(String[] args) {
		SpringApplication.run(ApplicationStart.class, args);
	}

}
