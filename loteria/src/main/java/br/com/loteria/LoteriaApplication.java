package br.com.loteria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport
public class LoteriaApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoteriaApplication.class, args);
	}

}
