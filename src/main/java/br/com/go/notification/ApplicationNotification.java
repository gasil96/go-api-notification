package br.com.go.notification;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
@OpenAPIDefinition(info = @Info(title = "${application.title}", version = "${application.version}"))
public class ApplicationNotification {

	public static void main(String[] args) {
		SpringApplication.run(ApplicationNotification.class, args);
	}

}
